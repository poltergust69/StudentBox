package com.studentbox.api.utils.mappers;

import com.studentbox.api.entities.user.Role;
import com.studentbox.api.models.user.role.RoleModel;

import java.util.List;

import static com.studentbox.api.utils.containers.ExceptionMessageContainer.UTILITY_CLASS_INITIALIZED_EXCEPTION_MESSAGE;

public class RoleMapper {
    private RoleMapper() {
        throw new IllegalStateException(UTILITY_CLASS_INITIALIZED_EXCEPTION_MESSAGE);
    }

    public static RoleModel mapToModel(Role role){
        RoleModel model = new RoleModel();

        model.setName(role.getName());

        return model;
    }

    public static List<RoleModel> mapAllToModel(List<Role> roles){
        return roles
                .stream()
                .map(RoleMapper::mapToModel)
                .toList();
    }
}
