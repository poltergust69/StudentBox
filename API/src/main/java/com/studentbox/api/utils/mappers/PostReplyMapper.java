package com.studentbox.api.utils.mappers;

import com.studentbox.api.entities.forum.PostReply;
import com.studentbox.api.entities.user.User;
import com.studentbox.api.models.post.reply.PostReplyModel;

import java.util.List;

import static com.studentbox.api.utils.containers.ExceptionMessageContainer.UTILITY_CLASS_INITIALIZED_EXCEPTION_MESSAGE;
import static java.util.Objects.isNull;

public class PostReplyMapper {
    private PostReplyMapper() {
        throw new IllegalStateException(UTILITY_CLASS_INITIALIZED_EXCEPTION_MESSAGE);
    }

    public static List<PostReplyModel> mapAllToModel(List<PostReply> replies, User currentUser){
        return replies.stream().map(reply -> mapToModel(reply, currentUser)).toList();
    }

    public static PostReplyModel mapToModel(PostReply reply, User currentUser){
        return new PostReplyModel(
                reply,
                reply.getLikes().size(),
                isNull(currentUser) ? Boolean.FALSE : reply.getLikes().stream().anyMatch(liker -> liker.getId().equals(currentUser.getId())));
    }
}
