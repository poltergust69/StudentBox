package com.studentbox.api.models.user;

import com.studentbox.api.entities.user.User;
import lombok.Data;

import java.util.UUID;

@Data
public class UserModel {
    private UUID id;
    private String username;
    private String avatarUrl;

    public UserModel(User user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.avatarUrl = user.getAvatarUrl();
    }
}
