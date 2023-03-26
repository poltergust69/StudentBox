package com.studentbox.api.models.user.role;

import com.studentbox.api.entities.user.Role;
import lombok.Data;

@Data
public class RoleModel {
    private String name;

    public RoleModel(Role role) {
        this.name = role.getName();
    }
}
