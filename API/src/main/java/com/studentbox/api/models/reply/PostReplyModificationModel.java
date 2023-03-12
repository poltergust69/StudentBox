package com.studentbox.api.models.reply;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostReplyModificationModel extends PostReplyCreationModel {
    public PostReplyModificationModel(String content) {
        super(content);
    }
}
