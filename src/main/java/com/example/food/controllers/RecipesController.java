package com.example.food.controllers;

import com.example.food.db.entities.Account;
import com.example.food.db.entities.Recipe;
import com.example.food.db.entities.RecipeComment;
import com.example.food.db.repositories.RecipeCommentRepo;
import com.example.food.db.repositories.RecipeRepo;
import com.example.food.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "/recipes")
public class RecipesController {
    private final RecipeRepo recipeRepo;
    private final UserService userService;
    private final RecipeCommentRepo commentRepo;

    @Autowired
    public RecipesController(RecipeRepo recipeRepo, UserService userService, RecipeCommentRepo commentRepo) {
        this.recipeRepo = recipeRepo;
        this.userService = userService;
        this.commentRepo = commentRepo;
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
        assert account.isPresent();

        recipe.setAuthor(account.get());
        recipeRepo.save(recipe);
        return "redirect:/recipes/" + recipe.getId();
    }

    @GetMapping(path = "/{id}")
    public String getRecipe(@PathVariable("id") int recipeId,
                            ModelMap modelMap, HttpServletRequest request) {
        Optional<Recipe> recipe = recipeRepo.findById(recipeId);
        assert recipe.isPresent();

        modelMap.put("recipe", recipe.get());
        return "RecipesId";
    }

    @ResponseBody
    @GetMapping(path = "{id}/comments", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RecipeComment> getComments(@PathVariable("id") int recipeId) {
        Optional<Recipe> post = recipeRepo.findById(recipeId);
        return post.map(Recipe::getComments).orElse(Collections.emptyList());
    }

    @PostMapping(path = "{id}/comments")
    public ResponseEntity<RecipeComment> createComment(@PathVariable("id") int postId,
                                                       @RequestParam("text") String content,
                                                       HttpServletRequest request) {
        Recipe recipe = recipeRepo.findById(postId).get();
        Optional<Account> account = userService.getCurrentUser(request);
        assert account.isPresent();

        RecipeComment comment = RecipeComment.builder()
                .content(content)
                .author(account.get())
                .recipe(recipe)
                .build();
        recipe.getComments().add(comment);

        commentRepo.save(comment);
        recipeRepo.save(recipe);

        return ResponseEntity.ok(comment);
    }
}
