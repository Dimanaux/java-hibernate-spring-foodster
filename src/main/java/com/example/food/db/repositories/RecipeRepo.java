package com.example.food.db.repositories;

import com.example.food.db.entities.Ingredient;
import com.example.food.db.entities.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface RecipeRepo extends JpaRepository<Recipe, Integer> {
    List<Recipe> findAllByAuthorId(int authorId);

    @Query(
            value = "SELECT * FROM recipe r INNER JOIN ingredient_recipe ir on r.id = ir.recipe_id INNER JOIN ingredient i on ir.ingredient_id = i.id WHERE NOT EXISTS (SELECT 1 FROM ingredient_recipe ir WHERE ir.recipe_id = r.id AND ir.ingredient_id NOT IN :ingredients);",
            nativeQuery = true
    )
    Set<Recipe> findAllByIngredientsIn(@Param("ingredients") List<Ingredient> ingredients);
}
