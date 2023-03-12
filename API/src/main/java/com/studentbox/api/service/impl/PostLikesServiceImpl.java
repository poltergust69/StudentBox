package com.studentbox.api.service.impl;

import com.studentbox.api.entities.forum.Post;
import com.studentbox.api.entities.forum.PostLike;
import com.studentbox.api.entities.user.User;
import com.studentbox.api.repository.PostLikeRepository;
import com.studentbox.api.service.PostLikesService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static java.util.Objects.isNull;

@Service
@AllArgsConstructor
public class PostLikesServiceImpl implements PostLikesService {
    private final PostLikeRepository postLikeRepository;

    @Override
    public Map<UUID, Integer> getLikesForPosts(List<Post> posts) {
        Map<UUID, Integer> postLikes = new HashMap<>();

        posts.forEach(post -> {
            var likes = getLikesForPost(post);
            postLikes.put(post.getId(), likes);
        });

        return postLikes;
    }

    @Override
    public Integer getLikesForPost(Post post) {
        return postLikeRepository.countPostLikeByPost(post);
    }

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

    @Override
    public boolean getIfLikedByCurrentUser(User user, Post post) {
        if(isNull(user)){
            return false;
        }

        return postLikeRepository.findPostLikeByPostAndUser(post, user).isPresent();
    }

    @Override
    public Map<UUID, Boolean> getIfPostsLikedByCurrentUser(User user, List<Post> posts) {
        Map<UUID, Boolean> postsLikedByUser = new HashMap<>();

        posts.forEach(post -> postsLikedByUser.put(post.getId(), getIfLikedByCurrentUser(user, post)));

        return postsLikedByUser;
    }
}