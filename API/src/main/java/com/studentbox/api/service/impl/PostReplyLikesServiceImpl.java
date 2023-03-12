package com.studentbox.api.service.impl;

import com.studentbox.api.entities.forum.PostReply;
import com.studentbox.api.entities.forum.PostReplyLike;
import com.studentbox.api.entities.user.User;
import com.studentbox.api.repository.PostReplyLikeRepository;
import com.studentbox.api.service.PostReplyLikesService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static java.util.Objects.isNull;

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

    @Override
    public void toggleLikeForReply(PostReply postReply, User user) {
        var replyLike = postReplyLikeRepository.findPostReplyLikeByUserAndReply(user, postReply);

        if(replyLike.isPresent()){
            postReplyLikeRepository.delete(replyLike.get());
        }
        else{
            PostReplyLike postReplyLike = new PostReplyLike(user, postReply);
            postReplyLikeRepository.save(postReplyLike);
        }
    }

    @Override
    public Map<UUID, Boolean> getRepliesLikedByUser(User user, List<PostReply> replies) {
        Map<UUID, Boolean> likedByUser = new HashMap<>();

        replies.forEach(reply -> likedByUser.put(reply.getId(), getReplyLikedByUser(user, reply)));

        return likedByUser;
    }

    @Override
    public boolean getReplyLikedByUser(User user, PostReply reply) {
        if(isNull(user)){
            return false;
        }

        return postReplyLikeRepository.findPostReplyLikeByUserAndReply(user, reply).isPresent();
    }
}
