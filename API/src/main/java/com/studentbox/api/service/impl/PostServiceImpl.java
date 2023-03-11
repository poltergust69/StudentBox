package com.studentbox.api.service.impl;

import com.studentbox.api.entities.forum.Post;
import com.studentbox.api.entities.user.User;
import com.studentbox.api.models.common.PaginationModel;
import com.studentbox.api.models.post.PostModel;
import com.studentbox.api.repository.PostRepository;
import com.studentbox.api.service.PostLikesService;
import com.studentbox.api.service.PostRepliesService;
import com.studentbox.api.service.PostService;
import com.studentbox.api.service.UserService;
import com.studentbox.api.utils.mappers.PostMapper;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

import static com.studentbox.api.utils.containers.ConstantsContainer.POSTS_DEFAULT_SORT_BY;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final PostLikesService postLikesService;
    private final PostRepliesService postRepliesService;
    private final UserService userService;

    private final static Logger logger = LoggerFactory.getLogger(PostServiceImpl.class);

    @Override
    public List<PostModel> getPageOfPosts(PaginationModel paginationModel) {
        Pageable pageable = PageRequest.of(paginationModel.getPageIndex(), paginationModel.getPageSize(), POSTS_DEFAULT_SORT_BY);
        var posts = postRepository.findAll(pageable);
        var postLikes = postLikesService.getLikesForPosts(posts.toList());
        var postReplies = postRepliesService.getRepliesForPosts(posts.toList());

        return PostMapper.mapAllToModel(posts.toList(), postLikes, postReplies);
    }

    @Override
    public boolean createNewPost(String title, String content, String authorizedUser) {
        UUID authorId = UUID.fromString(authorizedUser);
        Post post = new Post();

        post.setId(UUID.randomUUID());
        post.setTitle(title);
        post.setContent(content);

        Timestamp creationTime = Timestamp.from(Instant.now());

        post.setModifiedAt(creationTime);
        post.setCreatedAt(creationTime);

        User author = userService.findById(authorId);
        post.setAuthor(author);

        try{
            postRepository.save(post);
            return true;
        }
        catch (Exception e){
            logger.error(e.getMessage());
            return false;
        }
    }
}
