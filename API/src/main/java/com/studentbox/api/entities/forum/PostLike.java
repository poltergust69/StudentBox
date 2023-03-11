package com.studentbox.api.entities.forum;

import com.studentbox.api.entities.user.User;
import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name="post_likes")
public class PostLike {
    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="post_id")
    private Post post;
}
