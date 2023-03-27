package com.studentbox.api.entities.forum;

import com.studentbox.api.entities.user.User;
import com.studentbox.api.models.post.PostCreationModel;
import com.studentbox.api.models.post.PostModificationModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import static java.util.Objects.isNull;

@Data
@Entity
@Table(name="posts")
@NoArgsConstructor
public class Post {
    @Id
    private UUID id;

    @Column(name="title", length = 100)
    private String title;

    private String content;

    @Column(name="created_at")
    private Timestamp createdAt;

    @Column(name="modified_at")
    private Timestamp modifiedAt;

    @ManyToOne
    @JoinColumn(name="author_id")
    private User author;

    @OneToMany
    @JoinColumn(name="post_id")
    @OrderBy("modifiedAt")
    private List<PostReply> replies;

    @ManyToMany
    @JoinTable(name = "post_likes",
            joinColumns = @JoinColumn(name="post_id"),
            inverseJoinColumns = @JoinColumn(name="user_id"))
    private List<User> likes;

    public Post(PostCreationModel postCreationModel, User author){
        this.id = UUID.randomUUID();
        this.title = postCreationModel.getTitle();
        this.content = postCreationModel.getContent();
        this.author = author;
        this.modifiedAt = Timestamp.from(Instant.now());
        this.createdAt = Timestamp.from(Instant.now());
        this.replies = new LinkedList<>();
        this.likes = new LinkedList<>();
    }
    public Post(String title, String content, User author) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.content = content;
        this.author = author;
        this.modifiedAt = Timestamp.from(Instant.now());
        this.createdAt = Timestamp.from(Instant.now());
        this.replies = new LinkedList<>();
        this.likes = new LinkedList<>();
    }

    public void modifyPost(PostModificationModel postModificationModel){
        this.title = isNull(postModificationModel.getTitle()) ? this.title : postModificationModel.getTitle();
        this.content = isNull(postModificationModel.getContent()) ? this.content : postModificationModel.getContent();
        this.modifiedAt = Timestamp.from(Instant.now());
    }
}
