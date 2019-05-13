package com.example.food.services;

import com.example.food.db.entities.Account;
import com.example.food.db.entities.Dish;
import com.example.food.db.entities.Ingredient;
import com.example.food.db.entities.Recipe;
import com.example.food.db.repositories.IngredientRepo;
import com.example.food.db.repositories.RecipeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RecipeService {
    private final RecipeRepo recipeDao;
    private final IngredientRepo ingredientDao;

    @Autowired
    public RecipeService(RecipeRepo recipeDao, IngredientRepo ingredientDao) {
        this.recipeDao = recipeDao;
        this.ingredientDao = ingredientDao;
    }

    public Optional<Recipe> findById(int recipeId) {
        return recipeDao.findById(recipeId);
    }

    public void delete(Recipe recipe) {
        recipeDao.delete(recipe);
    }

    public List<Recipe> findAll() {
        return recipeDao.findAll();
    }

    public Recipe create(Dish dish, Account author, String title, String text, List<Integer> ids) {
        Recipe recipe = Recipe.builder()
                .dish(dish)
                .title(title)
                .author(author)
                .content(text)
                .build();
        List<Ingredient> allById = ingredientDao.findAllById(ids);
        recipe.getIngredients().addAll(allById);
        return recipeDao.save(recipe);
    }

    public Set<Recipe>  searchByIngredients(List<String> ingredientsNames) {
        List<Ingredient> allByName = ingredientDao.findAllByName(ingredientsNames);
        return recipeDao.findAllByIngredientsIn(allByName);
    }
}
