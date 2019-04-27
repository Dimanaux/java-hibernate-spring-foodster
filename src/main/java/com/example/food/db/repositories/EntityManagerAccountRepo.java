package com.example.food.db.repositories;

import com.example.food.db.entities.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class EntityManagerAccountRepo implements UserRepo {
    @PersistenceContext
    private final EntityManager em;

    @Autowired
    public EntityManagerAccountRepo(EntityManager em) {
        this.em = em;
    }

    private final RowMapper<Account> accountMapper = (rs, i) -> Account.builder()
            .id(rs.getInt("id"))
            .name(rs.getString("name"))
            .username(rs.getString("username"))
            .token(rs.getString("token"))
            .password(rs.getString("password"))
            .build();


    @Override
    public Optional<Account> findByUsername(String username) {
        Account account = em.find(Account.class, new Account() {{
            setUsername(username);
        }});
        em.detach(account);
        return Optional.ofNullable(account);
    }

    @Override
    public Optional<Account> findByToken(String token) {
        Account account = em.find(Account.class, new Account() {{
            setToken(token);
        }});
        em.detach(account);
        return Optional.ofNullable(account);
    }

    @Override
    public Account save(Account account) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(account);
        transaction.commit();
        return account;
    }
}
