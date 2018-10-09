package com.search.gamessearch.service;

import com.search.gamessearch.model.User;
import com.search.gamessearch.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//Securing web app
@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository repository){
        this.userRepository=repository;
    }

    @Override
    // interfejs funkcyjny
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        User userCheck= userRepository.findByUsername(username);

        UserDetails user= new org.springframework.security.core.userdetails.User(username,userCheck.getPassword(),true,
                true,true,true,AuthorityUtils.NO_AUTHORITIES);
        return user;
    }

}
