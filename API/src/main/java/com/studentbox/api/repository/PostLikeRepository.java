package com.studentbox.api.repository;

import com.studentbox.api.entities.forum.Post;
import com.studentbox.api.entities.forum.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PostLikeRepository extends JpaRepository<PostLike, UUID> {
    public Integer countPostLikeByPost(Post post);
}
