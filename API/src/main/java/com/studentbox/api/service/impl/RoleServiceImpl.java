package com.studentbox.api.service.impl;

import com.studentbox.api.models.RoleModel;
import com.studentbox.api.repository.RoleRepository;
import com.studentbox.api.service.RoleService;
import com.studentbox.api.utils.mappers.RoleMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public List<RoleModel> getAll() {
        var roles = roleRepository.findAll();

        return RoleMapper.mapRolesToModel(roles);
    }
}
