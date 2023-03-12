package com.studentbox.api.service;

import com.studentbox.api.entities.forum.Post;
import com.studentbox.api.models.common.PaginationModel;
import com.studentbox.api.models.post.PostCreationModel;
import com.studentbox.api.models.post.PostModel;
import com.studentbox.api.models.post.PostModificationModel;
import com.studentbox.api.models.reply.PostReplyCreationModel;
import com.studentbox.api.models.reply.PostReplyModificationModel;

import java.util.List;

public interface PostService {
    List<PostModel> getPage(PaginationModel paginationModel);
    Post findById(String id);
    PostModel findBasicById(String id);
    void create(PostCreationModel postCreationModel);
    void delete(String id);
    void update(String id, PostModificationModel postModificationModel);
    void like(String id);
    void createReply(String postId, PostReplyCreationModel postReplyModel);
    void updateReply(String postId, String replyId, PostReplyModificationModel postReplyModel);
    void deleteReply(String postId, String replyId);
    void likeReply(String postId, String replyId);
}