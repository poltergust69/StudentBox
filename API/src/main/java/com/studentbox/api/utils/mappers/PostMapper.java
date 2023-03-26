package com.studentbox.api.utils.mappers;

import com.studentbox.api.entities.forum.Post;
import com.studentbox.api.models.post.PostModel;
import java.util.List;

import static com.studentbox.api.utils.containers.ConstantsContainer.REPLY_COUNT_LIMITED;
import static com.studentbox.api.utils.containers.ExceptionMessageContainer.UTILITY_CLASS_INITIALIZED_EXCEPTION_MESSAGE;

public class PostMapper {
    private PostMapper() {
        throw new IllegalStateException(UTILITY_CLASS_INITIALIZED_EXCEPTION_MESSAGE);
    }
    public static PostModel mapToModel(Post post, Boolean limitedView){
        return new PostModel(
                post,
                post.getLikes().size(),
                post.getReplies()
                        .stream()
                        .limit(Boolean.TRUE.equals(limitedView) ? REPLY_COUNT_LIMITED : post.getReplies().size())
                        .map(PostReplyMapper::mapToModel)
                        .toList()
        );
    }

    public static List<PostModel> mapAllToModel(List<Post> posts, Boolean limitedView){
        return posts
                .stream()
                .map(post -> mapToModel(post, limitedView))
                .toList();
    }
}
