package com.studentbox.api.models.user;

import com.studentbox.api.entities.user.User;
import com.studentbox.api.entities.user.Role;
import lombok.Data;

@Data
public class UserDetailsModel {
    private String id;
    private String username;
    private Role role;
    private String email;

    public UserDetailsModel(User user){
        this.id = user.getUsername();
        this.username = user.getEmail();
        this.role = user.getRole();
        this.email = user.getEmail();
    }

    public UserDetailsModel() {
    }
}
