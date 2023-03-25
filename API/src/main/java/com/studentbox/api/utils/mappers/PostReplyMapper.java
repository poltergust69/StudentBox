package com.studentbox.api.utils.mappers;

import com.studentbox.api.entities.forum.PostReply;
import com.studentbox.api.models.reply.PostReplyModel;

import java.util.List;

import static com.studentbox.api.utils.containers.ExceptionMessageContainer.UTILITY_CLASS_INITIALIZED_EXCEPTION_MESSAGE;

public class PostReplyMapper {
    private PostReplyMapper() {
        throw new IllegalStateException(UTILITY_CLASS_INITIALIZED_EXCEPTION_MESSAGE);
    }

    public static List<PostReplyModel> mapAllToModel(List<PostReply> replies){
        return replies.stream().map(PostReplyMapper::mapToModel)
                .toList();
    }

    public static PostReplyModel mapToModel(PostReply reply){
        return new PostReplyModel(reply, reply.getLikes().size());
    }
}
