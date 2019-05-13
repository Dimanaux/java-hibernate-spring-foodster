package com.example.food.db.entities;

import lombok.*;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Entity(name = "Account")
@Table(name = "account")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"recipes", "posts", "password"})
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private transient String password;

    @Column(name = "name")
    private String name;

    @Column(name = "token")
    private transient String token;

    @OneToMany
    @JoinColumn(name = "author_id")
    private final transient List<Post> posts = new LinkedList<>();

    @OneToMany
    @JoinColumn(name = "author_id")
    private final transient List<Recipe> recipes = new LinkedList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id) &&
                Objects.equals(username, account.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}

