package com.studentbox.api.service;

import com.studentbox.api.entities.forum.Post;
import com.studentbox.api.models.reply.PostReplyModel;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface PostRepliesService {
    Map<UUID, List<PostReplyModel>> getRepliesForPosts(List<Post> posts);
    List<PostReplyModel> getRepliesForPost(Post post, Boolean findAll);
}
