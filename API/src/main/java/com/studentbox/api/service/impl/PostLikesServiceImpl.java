package com.studentbox.api.service.impl;

import com.studentbox.api.entities.forum.Post;
import com.studentbox.api.entities.forum.PostLike;
import com.studentbox.api.entities.user.User;
import com.studentbox.api.repository.PostLikeRepository;
import com.studentbox.api.service.PostLikesService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PostLikesServiceImpl implements PostLikesService {
    private final PostLikeRepository postLikeRepository;

    @Override
    public void toggleLike(Post post, User user) {
        var postLike = postLikeRepository.findPostLikeByPostAndUser(post, user);

        if(postLike.isPresent()){
            postLikeRepository.delete(postLike.get());
        }
        else{
            var userLikesPost = new PostLike(post, user);
            postLikeRepository.save(userLikesPost);
        }
    }


}
