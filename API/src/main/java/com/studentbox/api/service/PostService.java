package com.studentbox.api.service;

import com.studentbox.api.models.common.PaginationModel;
import com.studentbox.api.models.post.PostModel;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface PostService {
    List<PostModel> getPageOfPosts(PaginationModel paginationModel);
    boolean createNewPost(String title, String content, String authorizedUser);
}
