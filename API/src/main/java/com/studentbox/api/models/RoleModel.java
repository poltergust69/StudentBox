package com.studentbox.api.models;

import com.studentbox.api.entities.Role;
import lombok.Data;

@Data
public class RoleModel {
    private String name;

    public RoleModel(Role role) {
        this.name = role.getName();
    }
}
