package com.studentbox.api.utils.mappers;

import com.studentbox.api.entities.forum.Post;
import com.studentbox.api.entities.user.User;
import com.studentbox.api.models.post.PostModel;
import java.util.List;

import static com.studentbox.api.utils.containers.ConstantsContainer.DEFAULT_LIKES_COUNT;
import static com.studentbox.api.utils.containers.ConstantsContainer.REPLY_COUNT_LIMITED;
import static com.studentbox.api.utils.containers.ExceptionMessageContainer.UTILITY_CLASS_INITIALIZED_EXCEPTION_MESSAGE;
import static java.util.Objects.isNull;

public class PostMapper {
    private PostMapper() {
        throw new IllegalStateException(UTILITY_CLASS_INITIALIZED_EXCEPTION_MESSAGE);
    }
    public static PostModel mapToModel(Post post, Boolean limitedView, User currentUser){
        return new PostModel(
                post,
                post.getLikes().size(),
                post.getReplies()
                        .stream()
                        .limit(Boolean.TRUE.equals(limitedView) ? REPLY_COUNT_LIMITED : post.getReplies().size())
                        .map(reply -> PostReplyMapper.mapToModel(reply, currentUser))
                        .toList(),
                isNull(currentUser) ? Boolean.FALSE : post.getLikes().stream().anyMatch(u -> u.getId().equals(currentUser.getId()))
        );
    }

    public static List<PostModel> mapAllToModel(List<Post> posts, Boolean limitedView, User currentUser){
        return posts
                .stream()
                .map(post -> mapToModel(post, limitedView, currentUser))
                .toList();
    }
}
