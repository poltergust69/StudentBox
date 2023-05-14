package com.studentbox.api.service.user.impl;

import com.studentbox.api.entities.user.Role;
import com.studentbox.api.exception.NotFoundException;
import com.studentbox.api.models.user.role.RoleModel;
import com.studentbox.api.repository.user.RoleRepository;
import com.studentbox.api.service.user.RoleService;
import com.studentbox.api.utils.mappers.RoleMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.studentbox.api.utils.containers.ExceptionMessageContainer.ROLE_WITH_NAME_NOT_FOUND_EXCEPTION_MESSAGE;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;


    @Override
    public List<RoleModel> getAll() {
        var roles = roleRepository.findAll();

        return RoleMapper.mapAllToModel(roles);
    }

    @Override
    public Role getRoleByName(String name) {
        return roleRepository.findByName(name).orElseThrow(
                () -> new NotFoundException(String.format(ROLE_WITH_NAME_NOT_FOUND_EXCEPTION_MESSAGE, name))
        );
    }
}
