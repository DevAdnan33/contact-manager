package com.contactmanager.smartcontactmanager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.contactmanager.smartcontactmanager.dao.UserRepo;
import com.contactmanager.smartcontactmanager.entities.User;

@Controller
public class HomeController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        User user = new User();

        user.setName("Adnan Rashid");
        user.setEmail("adnanrashid111@gmail.com");
        user.setPassword("12345");
        user.setRole("user");
        user.setEnabled(true);
        user.setImageUrl("asdfsadf.png");

        userRepo.save(user);
        return "working";
    }
}
