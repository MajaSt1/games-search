package com.search.gamessearch.controller;

import com.search.gamessearch.model.Register;
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
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class UserController {
    @Autowired
    private UserRepository repository;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
        return new ModelAndView("login");
    }
    @RequestMapping(value = "/signup")
    public String registration(Model model){
        model.addAttribute("signupform", new Register());
        return "signup";
    }

    @RequestMapping(value = "/submituser", method = RequestMethod.POST)
    public String submit(@Valid @ModelAttribute("signupform") Register register, BindingResult bindingResult) {
        System.out.println(bindingResult.toString());
        if (!bindingResult.hasErrors()) {
            if (register.getPassword().equals(register.getPasswordCheck())) {
                String password = register.getPassword();
                BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
                String hashPwd = bc.encode(password);

                User newUser = new User();
                newUser.setPassword(hashPwd);
                newUser.setUsername(register.getUsername());

                if (repository.findByUsername(register.getUsername()) == null) { //check db
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
            return "signup"; //binding errors
        }
        return "redirect:/login";
    }
}
