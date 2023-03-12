package com.studentbox.api.models.auth;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthRequestModel {
    private String user;
    private String password;

    public AuthRequestModel(String user, String password) {
        this.user = user;
        this.password = password;
    }
}
