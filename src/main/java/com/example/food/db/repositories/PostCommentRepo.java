package com.example.food.db.repositories;

import com.example.food.db.entities.PostComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostCommentRepo extends JpaRepository<PostComment, Integer> {
}
