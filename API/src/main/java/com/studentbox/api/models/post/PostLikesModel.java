package com.studentbox.api.models.post;

import com.studentbox.api.entities.forum.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostLikesModel {
    private Post post;
    private Integer likes;
}
