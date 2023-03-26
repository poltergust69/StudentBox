package com.studentbox.api.models.post.reply;

import com.studentbox.api.entities.forum.PostReply;
import com.studentbox.api.models.user.UserModel;
import com.studentbox.api.utils.mappers.UserMapper;
import lombok.Data;

@Data
public class PostReplyModel {
    private String content;
    private UserModel author;
    private Integer likes;

    public PostReplyModel(PostReply reply, Integer likes){
        this.content = reply.getContent();
        this.author = UserMapper.mapToModel(reply.getAuthor());
    }
}
