package com.example.food.db.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "recipe_comment")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"author", "recipe"})
public class RecipeComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Account author;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    @Column(name = "content")
    private String content;

    @Column(name = "published_at", insertable = false)
    private LocalDateTime publishedAt;
}
