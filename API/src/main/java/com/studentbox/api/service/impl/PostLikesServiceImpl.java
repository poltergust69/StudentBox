package com.studentbox.api.service.impl;

import com.studentbox.api.entities.forum.Post;
import com.studentbox.api.models.post.PostLikesModel;
import com.studentbox.api.repository.PostLikeRepository;
import com.studentbox.api.service.PostLikesService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostLikesServiceImpl implements PostLikesService {
    private final PostLikeRepository postLikeRepository;

    @Override
    public Map<UUID, Integer> getLikesForPosts(List<Post> posts) {
        Map<UUID, Integer> postLikes = new HashMap<>();

        posts.forEach(post -> {
            var likes = postLikeRepository.countPostLikeByPost(post);
            postLikes.put(post.getId(), likes);
        });

        return postLikes;
    }
}
