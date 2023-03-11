package com.studentbox.api.models;

import com.studentbox.api.entities.User;
import com.studentbox.api.entities.Role;
import lombok.Data;

@Data
public class UserDetailsModel {
    private String id;
    private String username;
    private Role role;
    private String email;

    public UserDetailsModel(User user){
        this.id = user.getId().toString();
        this.username = user.getEmail();
        this.role = user.getRole();
        this.email = user.getEmail();
    }

    public UserDetailsModel() {
    }
}
