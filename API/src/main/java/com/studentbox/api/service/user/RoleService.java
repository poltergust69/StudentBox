package com.studentbox.api.service.user;

import com.studentbox.api.entities.user.Role;
import com.studentbox.api.models.user.role.RoleModel;

import java.util.List;

public interface RoleService {
    List<RoleModel> getAll();
    Role getRoleByName(String name);
}
