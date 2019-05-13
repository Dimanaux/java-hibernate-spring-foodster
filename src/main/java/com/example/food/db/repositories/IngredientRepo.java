package com.example.food.db.repositories;

import com.example.food.db.entities.Ingredient;

import java.util.List;

//@Repository
public interface IngredientRepo {
    List<Ingredient> findAll();

    Ingredient save(Ingredient ingredient);

    List<Ingredient> findAllById(List<Integer> ids);

    List<Ingredient> findAllByName(List<String> names);
}
