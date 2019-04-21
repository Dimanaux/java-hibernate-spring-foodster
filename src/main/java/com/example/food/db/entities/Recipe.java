package com.example.food.db.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "recipe")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"comments", "author", "dish", "ingredients"})
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "title")
    private String title;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Account author;

    @ManyToOne
    @JoinColumn(name = "dish_id")
    private Dish dish;

    @Column(name = "content")
    private String content;

    @Column(name = "published_at", insertable = false)
    private LocalDateTime publishedAt;

    @OneToMany
    @JoinColumn(name = "recipe_id")
    private final List<RecipeComment> comments = new LinkedList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "ingredient_recipe",
            joinColumns = {@JoinColumn(name = "recipe_id")},
            inverseJoinColumns = {@JoinColumn(name = "ingredient_id")}
    )
    private final List<Ingredient> ingredients = new LinkedList<>();
}
