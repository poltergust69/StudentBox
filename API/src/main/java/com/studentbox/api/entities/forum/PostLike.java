package com.studentbox.api.entities.forum;

import com.studentbox.api.entities.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name="post_likes")
@NoArgsConstructor
public class PostLike {
    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="post_id")
    private Post post;

    public PostLike(Post post, User user) {
        this.id = UUID.randomUUID();
        this.user = user;
        this.post = post;
    }
}
