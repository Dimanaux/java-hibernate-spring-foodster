package com.example.food.controllers;

import com.example.food.db.entities.Ingredient;
import com.example.food.db.repositories.IngredientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/ingredients")
public class IngredientsController {
    private IngredientRepo ingredientRepo;

    @Autowired
    public IngredientsController(IngredientRepo ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
    }

    @PostMapping
    public void doPost(@RequestParam("ingredient_name") String ingredientName) {
        ingredientRepo.save(new Ingredient(ingredientName));
    }

    @ResponseBody
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List doGet() {
        return ingredientRepo.findAll();
    }
}
