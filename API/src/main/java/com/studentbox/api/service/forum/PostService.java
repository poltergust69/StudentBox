package com.studentbox.api.service.forum;

import com.studentbox.api.entities.forum.Post;
import com.studentbox.api.models.common.PageModel;
import com.studentbox.api.models.common.PaginationModel;
import com.studentbox.api.models.post.PostCreationModel;
import com.studentbox.api.models.post.PostModel;
import com.studentbox.api.models.post.reply.PostReplyCreationModel;

import java.util.List;

public interface PostService {
    PageModel<PostModel> getPage(PaginationModel paginationModel);
    Post findById(String id);
    PostModel findBasicById(String id);
    void create(PostCreationModel postCreationModel);
    void delete(String id);
    void update(String id, PostCreationModel postModel);
    void like(String id);
    void createReply(String postId, PostReplyCreationModel postReplyModel);
    void updateReply(String postId, String replyId, PostReplyCreationModel postReplyModel);
    void deleteReply(String postId, String replyId);
    void likeReply(String postId, String replyId);
}
