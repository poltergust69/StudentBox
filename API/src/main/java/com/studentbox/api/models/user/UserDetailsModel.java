package com.studentbox.api.models.user;

import com.studentbox.api.entities.user.Role;
import com.studentbox.api.models.user.role.RoleModel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDetailsModel {
    private String id;
    private String username;
    private Role role;
    private String email;

    public UserDetailsModel(UserDetailsModel userDetailsModel){
        this.id = userDetailsModel.getId();
        this.username = userDetailsModel.getUsername();
        this.role = userDetailsModel.getRole();
        this.email = userDetailsModel.getEmail();
    }
}
