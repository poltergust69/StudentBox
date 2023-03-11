package com.studentbox.api.repository;

import com.studentbox.api.entities.forum.PostReply;
import com.studentbox.api.entities.forum.PostReplyLike;
import com.studentbox.api.models.reply.PostReplyModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PostReplyLikeRepository extends JpaRepository<PostReplyLike, UUID> {
    Integer countPostReplyLikeByReply(PostReply reply);
}
