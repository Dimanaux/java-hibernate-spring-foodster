package com.example.food.controllers;

import com.example.food.db.entities.Account;
import com.example.food.db.entities.Recipe;
import com.example.food.db.repositories.RecipeRepo;
import com.example.food.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
@RequestMapping(path = "/recipes")
public class RecipesController {
    private final RecipeRepo recipeRepo;
    private final UserService userService;

    @Autowired
    public RecipesController(RecipeRepo recipeRepo, UserService userService) {
        this.recipeRepo = recipeRepo;
        this.userService = userService;
    }

    @GetMapping
    public String getPosts(ModelMap modelMap, HttpServletRequest request) {
        modelMap.put("recipes", recipeRepo.findAll());
        return "RecipesIndex";
    }

    @GetMapping(path = "/new")
    public String getRecipeForm() {
        return "RecipesNew";
    }

    @PostMapping
    public String createRecipe(Recipe recipe, HttpServletRequest request) {
        Optional<Account> account = userService.getCurrentUser(request);
        recipe.setAuthor(account.get());
        recipeRepo.save(recipe);
        return "redirect:/recipes/" + recipe.getId();
    }

    @GetMapping(path = "/{id}")
    public String getRecipe(@PathVariable("id") int recipeId,
                            ModelMap modelMap, HttpServletRequest request) {
        Optional<Recipe> recipe = recipeRepo.findById(recipeId);
        modelMap.put("recipe", recipe.get());
        return "RecipesId";
    }

    // todo comments
}
