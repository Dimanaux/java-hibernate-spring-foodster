package com.example.food.db.repositories;

import com.example.food.db.entities.RecipeComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeCommentRepo extends JpaRepository<RecipeComment, Integer> {
}
