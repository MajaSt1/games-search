package com.search.gamessearch.controller;

import com.search.gamessearch.model.SignupForm;
import com.search.gamessearch.model.User;
import com.search.gamessearch.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class UserController {
    @Autowired
    private UserRepository repository;

    @RequestMapping(value = "signup")
    public String addGame(Model model){
        model.addAttribute("signupform", new SignupForm());
        return "signup";
    }

    @RequestMapping(value = "saveuser", method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute("signupform") SignupForm signupForm, BindingResult bindingResult) {
        System.out.println(bindingResult.toString());
        if (!bindingResult.hasErrors()) {
            if (signupForm.getPassword().equals(signupForm.getPasswordCheck())) {
                String password = signupForm.getPassword();
                BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
                String hashPwd = bc.encode(password);

                User newUser = new User();
                newUser.setPassword(hashPwd);
                newUser.setUsername(signupForm.getUsername());
                if (repository.findByUsername(signupForm.getUsername()) == null) {
                    repository.save(newUser);
                }
                else {
                    bindingResult.rejectValue("username", "error.userexists", "Username already exists");
                    return "signup";
                }
            }
            else {
                bindingResult.rejectValue("passwordCheck", "error.pwdmatch", "Passwords does not match");
                return "signup";
            }
        }
        else {
            return "signup";
        }
        return "redirect:/login";
    }
}
