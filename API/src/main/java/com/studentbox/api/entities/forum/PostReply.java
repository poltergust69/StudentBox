package com.studentbox.api.entities.forum;

import com.studentbox.api.entities.user.User;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@Entity
@Table(name="post_replies")
public class PostReply {
    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name="author_id")
    private User author;

    @ManyToOne
    @JoinColumn(name="post_id")
    private Post post;

    private String content;

    @Column(name="created_at")
    private Timestamp createdAt;

    @Column(name="modified_at")
    private Timestamp modifiedAt;
}
