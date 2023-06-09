package com.studentbox.api.models.post.reply;

import com.studentbox.api.entities.forum.PostReply;
import com.studentbox.api.models.user.UserModel;
import com.studentbox.api.utils.mappers.UserMapper;
import lombok.Data;

import java.util.UUID;

@Data
public class PostReplyModel {
    private UUID id;
    private String content;
    private UserModel author;
    private Integer likes;
    private boolean likedByCurrentUser;

    public PostReplyModel(PostReply reply, Integer likes, boolean likedByCurrentUser){
        this.id = reply.getId();
        this.content = reply.getContent();
        this.likes = likes;
        this.author = UserMapper.mapToModel(reply.getAuthor());
        this.likedByCurrentUser = likedByCurrentUser;
    }
}
