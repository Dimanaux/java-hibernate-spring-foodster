package com.example.food.db.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "post")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"comments", "author"})
@ToString(exclude = {"comments", "author"})
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "title")
    private String title;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Account author;

    @Column(name = "content")
    private String content;

    @Column(name = "published_at", insertable = false)
    private LocalDateTime publishedAt;

    @OneToMany
    @JoinColumn(name = "post_id")
    private List<PostComment> comments = new LinkedList<>();
}
