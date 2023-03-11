package com.studentbox.api.entities.forum;

import com.studentbox.api.entities.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name="post_reply_likes")
@NoArgsConstructor
public class PostReplyLike {
    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="reply_id")
    private PostReply reply;

    public PostReplyLike(User user, PostReply reply) {
        this.id = UUID.randomUUID();
        this.user = user;
        this.reply = reply;
    }
}
