package com.contactmanager.smartcontactmanager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.contactmanager.smartcontactmanager.dao.UserRepo;

@Controller
public class HomeController {

    @Autowired
    private UserRepo userRepo;

    @RequestMapping(value = "/home")
    public String base(Model model) {
        model.addAttribute("title", "Home - Contact Manager");
        return "home";
    }

    @RequestMapping(value = "/about")
    public String about(Model model) {
        model.addAttribute("title", "About - Contact Manager");
        return "about";
    }
}
