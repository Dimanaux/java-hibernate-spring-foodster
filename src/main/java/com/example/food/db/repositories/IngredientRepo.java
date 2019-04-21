package com.example.food.db.repositories;

import com.example.food.db.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepo extends JpaRepository<Ingredient, Integer> {
    // findAll
}
