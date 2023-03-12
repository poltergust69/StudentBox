package com.studentbox.api.service;

import com.studentbox.api.entities.forum.PostReply;
import com.studentbox.api.entities.user.User;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface PostReplyLikesService {
    Integer getLikesForReply(PostReply reply);
    Map<UUID, Integer> getLikesForReplies(List<PostReply> replies);
    void toggleLikeForReply(PostReply postReply, User user);
}
