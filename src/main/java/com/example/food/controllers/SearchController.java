package com.example.food.controllers;

import com.example.food.db.entities.Account;
import com.example.food.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Controller
@RequestMapping(path = "/search")
public class SearchController {
    private UserService userService;

    @Autowired
    public SearchController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String searchPage(@RequestParam("query") String query,
                             HttpServletRequest req,
                             ModelMap modelMap) {
        Optional<Account> user = userService.getCurrentUser(req);

        modelMap.put("user", user.get());
        modelMap.put("query", query);

        return "Search";
    }

    @GetMapping(path = ".json", produces = MediaType.APPLICATION_JSON_VALUE)
    protected void doGet(@RequestParam("query") String query,
                         HttpServletRequest req,
                         HttpServletResponse resp) {

        // todo
//        List<String> ingredientsNames = Arrays.asList(query.split(" "));
//
//        List<Recipe> recipesByIngredients = getRecipeService().getRecipesByIngredients(ingredientsNames);
//
//        resp.setContentType("text/json");
//        String json = getGson().toJson(recipesByIngredients.toArray());
//        resp.getWriter().write(json);
//        resp.getWriter().close();
    }
}
