package com.studentbox.api.utils.mappers;

import com.studentbox.api.entities.user.Role;
import com.studentbox.api.models.role.RoleModel;

import java.util.List;
import java.util.stream.Collectors;

public class RoleMapper {
    public static List<RoleModel> mapRolesToModel(List<Role> roles){
        return roles
                .stream()
                .map(RoleModel::new)
                .collect(Collectors.toList());
    }
}
