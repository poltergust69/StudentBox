package com.studentbox.api.service;

import com.studentbox.api.entities.forum.Post;
import com.studentbox.api.entities.user.User;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface PostLikesService {
    Map<UUID, Integer> getLikesForPosts(List<Post> posts);
    Integer getLikesForPost(Post post);
    void toggleLike(Post post, User user);
    boolean getIfLikedByCurrentUser(User user, Post post);
    Map<UUID, Boolean> getIfPostsLikedByCurrentUser(User user, List<Post> posts);
}
