package com.studentbox.api.models.post;

import com.studentbox.api.entities.forum.Post;
import com.studentbox.api.models.post.reply.PostReplyModel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class PostModel {
    private UUID id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private Integer likes;
    private List<PostReplyModel> replies;

    public PostModel(Post post, Integer likes, List<PostReplyModel> replies) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.createdAt = post.getCreatedAt().toLocalDateTime();
        this.modifiedAt = post.getModifiedAt().toLocalDateTime();
        this.likes = likes;
        this.replies = replies;
    }
}
