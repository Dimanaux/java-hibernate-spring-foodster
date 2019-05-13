package com.example.food.controllers;

import com.example.food.db.entities.Recipe;
import com.example.food.services.RecipeService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

@Controller
public class SearchController {
    private final RecipeService recipeService;
    private final Gson gson;

    @Autowired
    public SearchController(RecipeService recipeService, Gson gson) {
        this.recipeService = recipeService;
        this.gson = gson;
    }

    @GetMapping("/search")
    public String searchPage(@RequestParam(value = "query", required = false) String query,
                             HttpServletRequest req,
                             ModelMap modelMap) {
        modelMap.put("query", query);
        return "Search";
    }

    @ResponseBody
    @GetMapping(path = "/search.json", produces = MediaType.APPLICATION_JSON_VALUE)
    public String search(@RequestParam("query") String query,
                         HttpServletRequest request,
                         HttpServletResponse response) {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        List<String> ingredientsNames = Arrays.asList(query.split(" "));
        List<Recipe> recipes = recipeService.searchByIngredients(ingredientsNames);
        return gson.toJson(recipes.toArray());
    }
}
