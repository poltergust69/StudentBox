package com.studentbox.api.web;

import com.studentbox.api.models.RoleModel;
import com.studentbox.api.service.RoleService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private final RoleService roleService;

    @GetMapping("/roles")
    @ApiOperation(value="Get all Roles present in the system", response = RoleModel.class)
    public ResponseEntity<List<RoleModel>> getRoles(){
        return ResponseEntity.ok(roleService.getAll());
    }
}
