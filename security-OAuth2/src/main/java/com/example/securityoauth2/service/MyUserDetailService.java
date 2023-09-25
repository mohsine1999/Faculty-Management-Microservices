package com.example.securityoauth2.service;

import com.example.securityoauth2.entities.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private AppUserService appUserService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        AppUser users= appUserService.loadUserByName(username);
        if(users == null) {
            throw new UsernameNotFoundException(String.format("user not %s exist spring SECURITY  ",username));
        }

        System.out.println(users.getUsername()+" "+users.getPassword());
        return User.withUsername(users.getUsername())
                .password(users.getPassword())
                .authorities(users.getAuthorities())
                .build();
    }

}
