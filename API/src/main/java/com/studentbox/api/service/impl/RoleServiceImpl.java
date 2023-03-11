package com.studentbox.api.service.impl;

import com.studentbox.api.entities.user.Role;
import com.studentbox.api.exception.NotFoundException;
import com.studentbox.api.models.role.RoleModel;
import com.studentbox.api.repository.RoleRepository;
import com.studentbox.api.service.RoleService;
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

        return RoleMapper.mapRolesToModel(roles);
    }

    @Override
    public Role getRoleByName(String name) {
        return roleRepository.findByName(name).orElseThrow(
                () -> new NotFoundException(String.format(ROLE_WITH_NAME_NOT_FOUND_EXCEPTION_MESSAGE, name))
        );
    }
}
