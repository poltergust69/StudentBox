package com.studentbox.api.entities.forum;

import com.studentbox.api.entities.user.User;
import com.studentbox.api.models.post.PostCreationModel;
import com.studentbox.api.models.post.PostModificationModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;
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

    @JoinColumn(name="author_id")
    @ManyToOne
    private User author;

    public Post(PostCreationModel postCreationModel, User author){
        this.id = UUID.randomUUID();
        this.title = postCreationModel.getTitle();
        this.content = postCreationModel.getContent();
        this.author = author;
        this.modifiedAt = Timestamp.from(Instant.now());
        this.createdAt = Timestamp.from(Instant.now());
    }
    public Post(String title, String content, User author) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.content = content;
        this.author = author;
        this.modifiedAt = Timestamp.from(Instant.now());
        this.createdAt = Timestamp.from(Instant.now());
    }

    public void modifyPost(PostModificationModel postModificationModel){
        this.title = isNull(postModificationModel.getTitle()) ? this.title : postModificationModel.getTitle();
        this.content = isNull(postModificationModel.getContent()) ? this.content : postModificationModel.getContent();
        this.modifiedAt = Timestamp.from(Instant.now());
    }
}
