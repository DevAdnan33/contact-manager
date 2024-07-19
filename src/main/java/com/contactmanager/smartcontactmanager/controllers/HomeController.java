package com.contactmanager.smartcontactmanager.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.contactmanager.smartcontactmanager.dao.UserRepo;
import com.contactmanager.smartcontactmanager.entities.User;
import com.contactmanager.smartcontactmanager.helper.Message;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    private UserRepo userRepo;

    @RequestMapping(value = "/")
    public String base(Model model) {
        model.addAttribute("title", "Home - Contact Manager");
        return "home";
    }

    @RequestMapping(value = "/about")
    public String about(Model model) {
        model.addAttribute("title", "About - Contact Manager");
        return "about";
    }

    @RequestMapping(value = "/signuppage")
    public String signup(Model model) {
        User user = new User();
        model.addAttribute("title", "Signup - Contact Manager");
        model.addAttribute("user", user);
        return "signuppage";
    }

    @RequestMapping(value = "/loginpage")
    public String login(Model model) {
        model.addAttribute("title", "Login - Contact Manager");
        return "loginpage";
    }

    @RequestMapping(value = "/do_register", method = RequestMethod.POST)
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result,
            @RequestParam(value = "agreement", defaultValue = "false") boolean agreement, Model model,
            HttpSession session) {

        try {

            if (result.hasErrors()) {
                System.out.println("Errors" + result);
                return "signuppage";
            }

            if (agreement) {
                System.out.println("You have not agreed the terms and conditions!");
                throw new Exception("You have not agreed the terms and conditions!");
            }

            user.setRole("NORMAL");
            user.setEnabled(true);
            user.setImageUrl("default.png");

            userRepo.save(user);

            model.addAttribute("user", new User());
            session.setAttribute("message", new Message("Successfully Registered", "alert-success"));
            return "signuppage";

        } catch (Exception e) {
            model.addAttribute("user", user);
            session.setAttribute("message", new Message("Something went wrong!" + e.getMessage(), "alert-danger"));
            return "signuppage";
        }
    }
}
