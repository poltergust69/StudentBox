package com.studentbox.api.service.forum.impl;

import com.studentbox.api.entities.forum.Post;
import com.studentbox.api.entities.user.User;
import com.studentbox.api.exception.NotFoundException;
import com.studentbox.api.models.common.PaginationModel;
import com.studentbox.api.models.post.PostCreationModel;
import com.studentbox.api.models.post.PostModel;
import com.studentbox.api.models.post.PostModificationModel;

import com.studentbox.api.models.post.reply.PostReplyCreationModel;
import com.studentbox.api.models.post.reply.PostReplyModificationModel;
import com.studentbox.api.repository.forum.PostRepository;
import com.studentbox.api.service.forum.PostLikesService;
import com.studentbox.api.service.forum.PostRepliesService;
import com.studentbox.api.service.forum.PostService;
import com.studentbox.api.service.user.UserService;

import com.studentbox.api.utils.containers.SharedMethodContainer;

import com.studentbox.api.utils.mappers.PostMapper;
import com.studentbox.api.utils.validators.PostValidator;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.studentbox.api.utils.containers.ConstantsContainer.*;
import static com.studentbox.api.utils.containers.ExceptionMessageContainer.POST_NOT_FOUND_EXCEPTION_MESSAGE;
import static com.studentbox.api.utils.containers.SharedMethodContainer.isUserAuthenticated;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final PostLikesService postLikesService;
    private final PostRepliesService postRepliesService;
    private final UserService userService;
    private final Logger logger = LoggerFactory.getLogger(PostServiceImpl.class);

    @Override
    public List<PostModel> getPage(PaginationModel paginationModel) {
        boolean isAuthenticated = isUserAuthenticated();
        User user = isAuthenticated ? userService.findAuthenticatedUser() : null;

        Pageable pageable = PageRequest.of(paginationModel.getPageIndex(), paginationModel.getPageSize(), POSTS_DEFAULT_SORT_BY);
        var posts = postRepository.findAll(pageable);

        return PostMapper.mapAllToModel(posts.toList(), LIMITED_VIEW_ENABLED, user);
    }

    @Override
    public void create(PostCreationModel postCreationModel) {

        PostValidator.validatePost(postCreationModel);

        User author = userService.findAuthenticatedUser();
        Post post = new Post(postCreationModel, author);

        postRepository.save(post);
    }

    @Override
    public Post findById(String id){
        UUID postId = UUID.fromString(id);
        return postRepository.findById(postId).orElseThrow(
                () -> new NotFoundException(String.format(POST_NOT_FOUND_EXCEPTION_MESSAGE, id))
        );
    }

    @Override
    public PostModel findBasicById(String id) {
        boolean isAuthenticated = isUserAuthenticated();
        User user = isAuthenticated ? userService.findAuthenticatedUser() : null;

        var post = findById(id);
        return PostMapper.mapToModel(post, LIMITED_VIEW_DISABLED, user);
    }

    @Override
    public void delete(String id) {
        var post = findById(id);

        postRepository.delete(post);
    }

    @Override
    public void update(String id, PostModificationModel postModificationModel) {
        Post post = findById(id);

        PostValidator.validatePost(postModificationModel);

        post.modifyPost(postModificationModel);

        postRepository.save(post);
    }

    @Override
    public void like(String id) {
        Post post = findById(id);
        User user = userService.findAuthenticatedUser();
        postLikesService.toggleLike(post, user);
    }

    @Override
    public void createReply(String postId, PostReplyCreationModel postReplyModel) {
        Post post = findById(postId);
        User user = userService.findAuthenticatedUser();

        postRepliesService.create(post, user, postReplyModel);
    }

    @Override
    public void updateReply(String postId, String replyId, PostReplyModificationModel postReplyModel) {
        postRepliesService.update(postId, replyId, postReplyModel);
    }

    @Override
    public void deleteReply(String postId, String replyId) {
        postRepliesService.delete(postId, replyId);
    }

    @Override
    public void likeReply(String postId, String replyId) {
        User user = userService.findAuthenticatedUser();

        postRepliesService.like(postId, replyId, user);
    }
}
