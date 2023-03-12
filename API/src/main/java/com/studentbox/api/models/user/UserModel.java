package com.studentbox.api.models.user;

import com.studentbox.api.entities.user.User;
import lombok.Data;

import java.util.UUID;

@Data
public class UserModel {
    public UUID id;
    public String username;
    public String avatarUrl;

    public UserModel(User user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.avatarUrl = user.getAvatarUrl();
    }
}
