package com.studentbox.api.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.studentbox.api.entities.user.enums.RoleType;
import com.studentbox.api.models.auth.AuthRequestModel;
import com.studentbox.api.models.auth.AuthResponseModel;
import com.studentbox.api.models.company.RegisterCompanyDetails;
import com.studentbox.api.models.role.RoleModel;
import com.studentbox.api.models.user.RegisterUserDetails;
import com.studentbox.api.service.RoleService;
import com.studentbox.api.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private final RoleService roleService;
    private final UserService userService;

    @GetMapping("/roles")
    @ApiOperation(value="Get all Roles present in the system", response = RoleModel.class)
    public ResponseEntity<List<RoleModel>> getRoles(){
        return ResponseEntity.ok(roleService.getAll());
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseModel> login(
        AuthRequestModel authRequestModel
    ) throws JsonProcessingException {
        return ResponseEntity.ok(userService.login(authRequestModel));
    }
}
