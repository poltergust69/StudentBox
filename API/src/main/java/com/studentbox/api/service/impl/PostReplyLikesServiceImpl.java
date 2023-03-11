package com.studentbox.api.service.impl;

import com.studentbox.api.entities.forum.PostReply;
import com.studentbox.api.repository.PostReplyLikeRepository;
import com.studentbox.api.service.PostReplyLikesService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PostReplyLikesServiceImpl implements PostReplyLikesService {
    private final PostReplyLikeRepository postReplyLikeRepository;

    @Override
    public Integer getLikesForReply(PostReply reply) {
        return postReplyLikeRepository.countPostReplyLikeByReply(reply);
    }

    @Override
    public Map<UUID, Integer> getLikesForReplies(List<PostReply> replies) {
        Map<UUID, Integer> repliesLikes = new HashMap<>();

        replies.forEach(reply -> {
            int likes = postReplyLikeRepository.countPostReplyLikeByReply(reply);
            repliesLikes.put(reply.getId(), likes);
        });

        return repliesLikes;
    }
}
