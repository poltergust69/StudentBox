package com.studentbox.api.models.post;

import com.studentbox.api.entities.forum.Post;
import com.studentbox.api.models.post.reply.PostReplyModel;
import com.studentbox.api.models.user.UserModel;
import com.studentbox.api.utils.mappers.UserMapper;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class PostModel {
    private UUID id;
    private String title;
    private String content;
    private Timestamp createdAt;
    private Timestamp modifiedAt;
    private Integer likes;
    private boolean likedByCurrentUser;
    private List<PostReplyModel> replies;
    private UserModel author;

    public PostModel(Post post, Integer likes, List<PostReplyModel> replies, boolean likedByCurrentUser) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
        this.likes = likes;
        this.replies = replies;
        this.likedByCurrentUser = likedByCurrentUser;
        this.author = UserMapper.mapToModel(post.getAuthor());
    }
}
