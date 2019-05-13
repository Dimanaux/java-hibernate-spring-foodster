package com.example.food.db.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "post_comment")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"author", "post"})
public class PostComment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Account author;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private transient Post post;

    @Column(name = "content")
    private String content;

    @Column(name = "published_at", insertable = false)
    private LocalDateTime publishedAt;
}
