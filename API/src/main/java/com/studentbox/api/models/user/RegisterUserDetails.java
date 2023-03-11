package com.studentbox.api.models.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserDetails {
    private String username;
    private String email;
    private String password;
    private String avatarUrl;
    private String role;
}
