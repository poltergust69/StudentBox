package com.studentbox.api.models.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostModificationModel extends PostCreationModel {
    private String id;
}
