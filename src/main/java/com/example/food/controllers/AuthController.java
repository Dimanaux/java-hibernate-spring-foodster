package com.example.food.controllers;

import com.example.food.db.entities.Account;
import com.example.food.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Controller
public class AuthController {
    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/auth")
    public String loginPage() {
        return "Auth";
    }

    @PostMapping("/auth")
    public String authenticate(HttpServletRequest request, HttpServletResponse response) {
        Optional<Account> user = userService.authenticate(request);
        user.ifPresent(u -> userService.authorize(request, u));

        if (user.isEmpty()) {
            return "redirect:/auth?error='wrong credentials'";
        }

        if (request.getParameter("remember_me") != null) {
            userService.remember(response, user.get());
        }
        return "redirect:/profile";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        userService.logout(request, response);
        return "redirect:/auth";
    }
}
