package com.studentbox.api.repository.forum;

import com.studentbox.api.entities.forum.Post;
import com.studentbox.api.entities.forum.PostReply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PostReplyRepository extends JpaRepository<PostReply, UUID> {
    Page<PostReply> getPostRepliesByPost(Post post, Pageable pageable);
    List<PostReply> getPostRepliesByPostOrderByModifiedAtDesc(Post post);
}
