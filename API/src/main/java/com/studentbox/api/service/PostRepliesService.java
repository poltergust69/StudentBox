package com.studentbox.api.service;

import com.studentbox.api.entities.forum.Post;
import com.studentbox.api.entities.forum.PostReply;
import com.studentbox.api.entities.user.User;
import com.studentbox.api.models.reply.PostReplyCreationModel;
import com.studentbox.api.models.reply.PostReplyModel;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface PostRepliesService {
    Map<UUID, List<PostReplyModel>> getRepliesForPosts(List<Post> posts);
    List<PostReplyModel> getRepliesForPost(Post post, Boolean findAll);
    PostReply findById(String id);
    void create(Post post, User author, PostReplyCreationModel postReplyCreationModel);
    void update(String postId, String replyId, PostReplyCreationModel postReplyCreationModel);
    void delete(String postId, String replyId);
    void like(String postId, String replyId, User user);
}
