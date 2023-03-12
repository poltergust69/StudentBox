package com.studentbox.api.utils.mappers;

import com.studentbox.api.entities.forum.Post;
import com.studentbox.api.models.post.PostModel;
import com.studentbox.api.models.reply.PostReplyModel;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.studentbox.api.utils.containers.ConstantsContainer.DEFAULT_LIKES_COUNT;
import static java.util.stream.Collectors.toList;

public class PostMapper {
    public static PostModel mapToModel(Post post, Integer likes, List<PostReplyModel> replies, boolean likedByCurrentUser){
        return new PostModel(post, likes, replies, likedByCurrentUser);
    }

    public static List<PostModel> mapAllToModel(
            List<Post> posts,
            Map<UUID, Integer> postsLikes,
            Map<UUID, List<PostReplyModel>> postsReplies,
            Map<UUID, Boolean> postsLikedByUser){
        return posts.stream().map(post ->
                mapToModel(
                        post,
                        postsLikes.getOrDefault(post.getId(), DEFAULT_LIKES_COUNT),
                        postsReplies.getOrDefault(post.getId(), Collections.emptyList()),
                        postsLikedByUser.getOrDefault(post.getId(), Boolean.FALSE)
                )).toList();
    }
}
