package com.studentbox.api.utils.mappers;

import com.studentbox.api.entities.forum.PostReply;
import com.studentbox.api.models.reply.PostReplyModel;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.studentbox.api.utils.containers.ConstantsContainer.DEFAULT_LIKES_COUNT;

public class PostReplyMapper {
    public static List<PostReplyModel> mapAllToModel(
            List<PostReply> replies,
            Map<UUID, Integer> repliesLikes,
            Map<UUID, Boolean> repliesLikedByUser){
        return replies.stream().map(
                reply -> mapToModel(
                            reply,
                            repliesLikes.getOrDefault(reply.getId(), DEFAULT_LIKES_COUNT),
                            repliesLikedByUser.getOrDefault(reply.getId(), Boolean.FALSE)
                        )
        ).toList();
    }

    public static PostReplyModel mapToModel(PostReply reply, Integer likes, Boolean likedByUser){
        return new PostReplyModel(reply, likes, likedByUser);
    }
}
