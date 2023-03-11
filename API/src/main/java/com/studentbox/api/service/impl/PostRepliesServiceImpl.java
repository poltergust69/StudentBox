package com.studentbox.api.service.impl;

import com.studentbox.api.entities.forum.Post;
import com.studentbox.api.models.reply.PostReplyModel;
import com.studentbox.api.repository.PostReplyRepository;
import com.studentbox.api.service.PostRepliesService;
import com.studentbox.api.service.PostReplyLikesService;
import com.studentbox.api.utils.mappers.PostReplyMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.studentbox.api.utils.containers.ConstantsContainer.DEFAULT_PAGE_INDEX;
import static com.studentbox.api.utils.containers.ConstantsContainer.REPLIES_DEFAULT_PAGE_SIZE;

@Service
@AllArgsConstructor
public class PostRepliesServiceImpl implements PostRepliesService {
    private final PostReplyRepository postReplyRepository;
    private final PostReplyLikesService postReplyLikesService;

    @Override
    public Map<UUID, List<PostReplyModel>> getRepliesForPosts(List<Post> posts) {
        Map<UUID, List<PostReplyModel>> repliesForPosts = new HashMap<>();

        posts.forEach(post -> repliesForPosts.put(post.getId(), getRepliesForPost(post, Boolean.FALSE)));

        return repliesForPosts;
    }

    @Override
    public List<PostReplyModel> getRepliesForPost(Post post, Boolean findAll) {
        Pageable pageable = findAll ? Pageable.unpaged() : PageRequest.of(DEFAULT_PAGE_INDEX, REPLIES_DEFAULT_PAGE_SIZE);
        var repliesForPost = postReplyRepository.getPostRepliesByPost(post, pageable);
        var repliesLikesForPost = postReplyLikesService.getLikesForReplies(repliesForPost.toList());
        return PostReplyMapper.mapAllToModel(repliesForPost.toList(), repliesLikesForPost);
    }
}
