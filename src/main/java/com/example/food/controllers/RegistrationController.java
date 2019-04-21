package com.example.food.controllers;

import com.example.food.db.entities.Account;
import com.example.food.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping(path = "/registration")
public class RegistrationController {
    private UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    protected String register(HttpServletRequest req) {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String name = req.getParameter("name");

        Matcher usernameMatcher = Pattern.compile("[A-Za-z][A-Za-z0-9_]{3,15}").matcher(username);
        if (!usernameMatcher.matches()) {
            return "redirect:/registration?error='username must contain 4-15 symbols from a-z _ and 0-9'";
        }

        Matcher passwordMatcher = Pattern.compile("[A-Za-z0-9!@#$%^&*-=_+?.,]{6,24}").matcher(username);
        if (!passwordMatcher.matches()) {
            return "redirect:/registration?error='password must contain 6-24 symbols'";

        }

        Account account = userService.createUser(username, password, name);
        userService.authorize(req, account);

        return "redirect:/profile";
    }

    @GetMapping
    public String registrationPage() {
        return "Registration";
    }
}
