package com.studentbox.api.service;

import com.studentbox.api.entities.forum.Post;
import com.studentbox.api.entities.user.User;

public interface PostLikesService {
    void toggleLike(Post post, User user);
}
