package com.example.food.db.repositories;

import com.example.food.db.entities.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.Optional;

@Repository
public class EntityManagerAccountRepo implements UserRepo {
    private final EntityManager em;

    @Autowired
    public EntityManagerAccountRepo(@Qualifier("entityManagerFactory") EntityManagerFactory factory) {
        this.em = factory.createEntityManager();
    }

    @Override
    public Optional<Account> findByUsername(String username) {
        Optional<Account> account = em
                .createQuery("FROM Account WHERE username = :username", Account.class)
                .setParameter("username", username)
                .getResultStream().findAny();
        account.ifPresent(em::detach);
        return account;
    }

    @Override
    public Optional<Account> findByToken(String token) {
        Optional<Account> account = em
                .createQuery("FROM Account WHERE token = :token", Account.class)
                .setParameter("token", token)
                .getResultStream().findAny();
        account.ifPresent(em::detach);
        return account;
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
