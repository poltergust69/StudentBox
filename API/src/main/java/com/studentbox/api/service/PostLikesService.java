package com.studentbox.api.service;

import com.studentbox.api.entities.forum.Post;
import com.studentbox.api.models.post.PostLikesModel;
import com.studentbox.api.models.reply.PostReplyModel;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface PostLikesService {
    Map<UUID, Integer> getLikesForPosts(List<Post> posts);
}
