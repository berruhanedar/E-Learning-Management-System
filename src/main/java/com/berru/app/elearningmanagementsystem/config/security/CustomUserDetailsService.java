package com.berru.app.elearningmanagementsystem.config.security;

import com.berru.app.elearningmanagementsystem.entity.User;
import com.berru.app.elearningmanagementsystem.enums.status.ActiveStatus;
import com.berru.app.elearningmanagementsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = this.userService.getUserByEmailAndStatus(email, ActiveStatus.ACTIVE.value());

        CustomUserDetails customUserDetails = new CustomUserDetails(user);

        return customUserDetails;

    }
}