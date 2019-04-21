package com.example.food.db.entities;

import lombok.*;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "account")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"recipes", "posts"})
@ToString(exclude = {"recipes", "posts", "password"})
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "token")
    private String token;

    @OneToMany
    @JoinColumn(name = "author_id")
    private final List<Post> posts = new LinkedList<>();

    @OneToMany
    @JoinColumn(name = "author_id")
    private final List<Recipe> recipes = new LinkedList<>();
}

