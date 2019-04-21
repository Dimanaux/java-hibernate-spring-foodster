package com.example.food.db.repositories;

import com.example.food.db.entities.Post;
import com.example.food.db.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepo extends JpaRepository<Post, Integer> {
//    @Query("from Post where author_id = :author_id")
    List<Post> findAllByAuthorId(@Param("author_id") Integer authorId);
    List<Post> findAllByAuthor(Account author);
}
