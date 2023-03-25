package com.studentbox.api.service.impl;

import com.studentbox.api.entities.forum.Post;
import com.studentbox.api.entities.forum.PostReply;
import com.studentbox.api.entities.user.User;
import com.studentbox.api.exception.NotFoundException;
import com.studentbox.api.models.reply.PostReplyCreationModel;
import com.studentbox.api.repository.PostReplyRepository;
import com.studentbox.api.service.PostRepliesService;
import com.studentbox.api.service.PostReplyLikesService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

import static com.studentbox.api.utils.containers.ExceptionMessageContainer.POST_REPLY_NOT_FOUND_EXCEPTION_MESSAGE;
import static com.studentbox.api.utils.validators.PostValidator.validatePostReply;

@Service
@AllArgsConstructor
public class PostRepliesServiceImpl implements PostRepliesService {
    private final PostReplyRepository postReplyRepository;
    private final PostReplyLikesService postReplyLikesService;

    @Override
    public PostReply findById(String id) {
        UUID replyId = UUID.fromString(id);

        return postReplyRepository.findById(replyId).orElseThrow(
                () -> new NotFoundException(String.format(POST_REPLY_NOT_FOUND_EXCEPTION_MESSAGE, id))
        );
    }

    @Override
    public void create(Post post, User author, PostReplyCreationModel postReplyCreationModel) {
        validatePostReply(postReplyCreationModel);

        PostReply postReply = new PostReply(post, author, postReplyCreationModel.getContent());

        postReplyRepository.save(postReply);
    }

    @Override
    public void update(String postId, String replyId, PostReplyCreationModel postReplyCreationModel) {
        validateReplyBelongsToPost(postId, replyId);

        PostReply postReply = findById(replyId);

        validatePostReply(postReplyCreationModel);

        postReply.setContent(postReplyCreationModel.getContent());
        postReply.setModifiedAt(Timestamp.from(Instant.now()));

        postReplyRepository.save(postReply);
    }

    @Override
    public void delete(String postId, String replyId) {
        validateReplyBelongsToPost(postId, replyId);
        var reply = findById(replyId);
        postReplyRepository.delete(reply);
    }

    @Override
    public void like(String postId, String replyId, User user) {
        validateReplyBelongsToPost(postId, replyId);
        var reply = findById(replyId);

        postReplyLikesService.toggleLikeForReply(reply, user);
    }

    private void validateReplyBelongsToPost(String postId, String replyId){
        PostReply postReply = findById(replyId);
        Post post = postReply.getPost();

        if(!post.getId().toString().equals(postId)){
            throw new NotFoundException(String.format(POST_REPLY_NOT_FOUND_EXCEPTION_MESSAGE, replyId));
        }
    }
}
