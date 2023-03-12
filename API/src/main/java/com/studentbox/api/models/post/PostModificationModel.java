package com.studentbox.api.models.post;

import com.studentbox.api.entities.forum.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

import static java.util.Objects.isNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostModificationModel extends PostCreationModel {
    private String id;
}
