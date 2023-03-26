package com.studentbox.api.utils.mappers;

import com.studentbox.api.entities.user.Role;
import com.studentbox.api.models.user.role.RoleModel;

import java.util.List;

import static com.studentbox.api.utils.containers.ExceptionMessageContainer.UTILITY_CLASS_INITIALIZED_EXCEPTION_MESSAGE;

public class RoleMapper {
    private RoleMapper() {
        throw new IllegalStateException(UTILITY_CLASS_INITIALIZED_EXCEPTION_MESSAGE);
    }
    public static List<RoleModel> mapRolesToModel(List<Role> roles){
        return roles
                .stream()
                .map(RoleModel::new)
                .toList();
    }
}
