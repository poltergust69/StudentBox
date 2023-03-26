package com.studentbox.api.service.forum.impl;

import com.studentbox.api.entities.forum.PostReply;
import com.studentbox.api.entities.forum.PostReplyLike;
import com.studentbox.api.entities.user.User;
import com.studentbox.api.repository.forum.PostReplyLikeRepository;
import com.studentbox.api.service.forum.PostReplyLikesService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PostReplyLikesServiceImpl implements PostReplyLikesService {
    private final PostReplyLikeRepository postReplyLikeRepository;

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
}
