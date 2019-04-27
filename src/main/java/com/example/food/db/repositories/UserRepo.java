package com.example.food.db.repositories;

import com.example.food.db.entities.Account;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepo {
    Optional<Account> findByUsername(@Param("username") String username);

    Optional<Account> findByToken(@Param("token") String token);

    Account save(Account account);
}
