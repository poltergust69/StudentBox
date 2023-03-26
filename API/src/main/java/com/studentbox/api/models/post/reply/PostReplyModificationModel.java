package com.studentbox.api.models.post.reply;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostReplyModificationModel extends PostReplyCreationModel {
    public PostReplyModificationModel(String content) {
        super(content);
    }
}
