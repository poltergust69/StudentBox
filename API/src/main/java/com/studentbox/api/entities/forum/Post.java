package com.studentbox.api.entities.forum;

import com.studentbox.api.entities.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@Entity
@Table(name="posts")
@NoArgsConstructor
public class Post {
    @Id
    private UUID id;

    @Column(name="title", length = 100)
    private String title;

    private String content;

    @Column(name="created_at")
    private Timestamp createdAt;

    @Column(name="modified_at")
    private Timestamp modifiedAt;

    @JoinColumn(name="author_id")
    @ManyToOne
    private User author;

    public Post(String title, String content, User author) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.content = content;
        this.author = author;
    }
}
