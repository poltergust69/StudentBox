package com.studentbox.api.service;

import com.studentbox.api.entities.forum.PostReply;
import com.studentbox.api.entities.user.User;

public interface PostReplyLikesService {
    void toggleLikeForReply(PostReply postReply, User user);
}
