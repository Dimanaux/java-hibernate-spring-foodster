package com.example.food.controllers;

import com.example.food.db.entities.Account;
import com.example.food.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
@RequestMapping(path = {"/profile"})
public class ProfileController {
    private final UserService userService;

    @Autowired
    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getMyProfile(ModelMap modelMap, HttpServletRequest request) {
        Optional<Account> account = userService.getCurrentUser(request);
        modelMap.put("user", account.get());
        modelMap.put("recipes", account.get().getRecipes());
        return "Profile";
    }

    @RequestMapping(path = {"/edit"}, method = RequestMethod.GET)
    public String getProfileEditForm(ModelMap modelMap) {
        return "ProfileEdit";
    }

    @RequestMapping(path = {"/edit"}, method = RequestMethod.POST)
    public String editProfile(Account account) {
        // todo
        return "redirect:/profile";
    }
}
