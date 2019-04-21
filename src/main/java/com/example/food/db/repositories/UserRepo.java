package com.example.food.db.repositories;

import com.example.food.db.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<Account, Integer> {
    Optional<Account> findByUsername(@Param("username") String username);

    @Query("SELECT a FROM Account a WHERE a.token = :token ")
    Optional<Account> findByToken(@Param("token") String token);
}
