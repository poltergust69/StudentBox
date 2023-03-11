package com.studentbox.api.service;

import com.studentbox.api.entities.forum.PostReply;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface PostReplyLikesService {
    public Integer getLikesForReply(PostReply reply);
    public Map<UUID, Integer> getLikesForReplies(List<PostReply> replies);
}
