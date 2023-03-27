package com.studentbox.api.utils.validators;

import com.studentbox.api.exception.NotValidException;
import com.studentbox.api.models.post.PostCreationModel;
import com.studentbox.api.models.post.PostModel;
import com.studentbox.api.models.post.reply.PostReplyCreationModel;

import static com.studentbox.api.utils.containers.ConstantsContainer.POST_TITLE_MAX_LENGTH;
import static com.studentbox.api.utils.containers.ExceptionMessageContainer.*;
import static java.util.Objects.isNull;

public class PostValidator {
    private PostValidator() {
        throw new IllegalStateException(UTILITY_CLASS_INITIALIZED_EXCEPTION_MESSAGE);
    }

    public static void validatePost(PostCreationModel postCreationModel){
        if(!isTitleValid(postCreationModel.getTitle())){
            throw new NotValidException(NOT_VALID_POST_TITLE_EXCEPTION_MESSAGE);
        }
        if(!isContentValid(postCreationModel.getContent())){
            throw new NotValidException(NOT_VALID_POST_CONTENT_EXCEPTION_MESSAGE);
        }
    }

    public static void validatePostReply(PostReplyCreationModel postReplyCreationModel){
        if(!isContentValid(postReplyCreationModel.getContent())){
            throw new NotValidException(NOT_VALID_REPLY_CONTENT_EXCEPTION_MESSAGE);
        }
    }

    private static boolean isTitleValid(String title){
        return !isNull(title) && !title.isBlank() && title.length() <= POST_TITLE_MAX_LENGTH;
    }

    private static boolean isContentValid(String content){
        return !isNull(content) && !content.isBlank();
    }
}
