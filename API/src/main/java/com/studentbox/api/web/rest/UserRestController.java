package com.studentbox.api.web.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.studentbox.api.models.auth.AuthRequestModel;
import com.studentbox.api.models.auth.AuthResponseModel;
import com.studentbox.api.models.user.role.RoleModel;
import com.studentbox.api.models.sendgrid.ResetPasswordModel;
import com.studentbox.api.service.user.RoleService;
import com.studentbox.api.service.user.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserRestController {
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
    @PostMapping("/refresh-token")
    public ResponseEntity<AuthResponseModel> refreshToken(
            @RequestParam String refreshToken
    ) throws JsonProcessingException {
        return ResponseEntity.ok(userService.refreshToken(refreshToken));
    }

    @PutMapping("/forgot-password")
    public ResponseEntity requestForgotPassword(
            @RequestParam String email
    ){
        userService.requestForgotPasswordCode(email);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/reset-password")
    public ResponseEntity requestForgotPassword(
            ResetPasswordModel resetPasswordModel
    ){
        userService.resetPassword(resetPasswordModel);
        return ResponseEntity.ok().build();
    }
}
