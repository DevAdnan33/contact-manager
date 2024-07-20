package com.contactmanager.smartcontactmanager.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.contactmanager.smartcontactmanager.dao.UserRepo;
import com.contactmanager.smartcontactmanager.entities.User;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Fetch user from database
        User user = userRepo.getUserByUserName(username);
        if (user.getEmail() == null) {
            throw new UsernameNotFoundException("User Not Found!");
        }

        CustomUserDetail customUserDetail = new CustomUserDetail(user);

        return customUserDetail;    

    }

}
