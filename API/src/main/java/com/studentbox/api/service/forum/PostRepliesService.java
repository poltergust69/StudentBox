package com.studentbox.api.service.forum;

import com.studentbox.api.entities.forum.Post;
import com.studentbox.api.entities.forum.PostReply;
import com.studentbox.api.entities.user.User;
import com.studentbox.api.models.post.reply.PostReplyCreationModel;

public interface PostRepliesService {
    PostReply findById(String id);
    void create(Post post, User author, PostReplyCreationModel postReplyCreationModel);
    void update(String postId, String replyId, PostReplyCreationModel postReplyCreationModel);
    void delete(String postId, String replyId);
    void like(String postId, String replyId, User user);
}
