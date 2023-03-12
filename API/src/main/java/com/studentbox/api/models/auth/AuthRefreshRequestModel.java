package com.studentbox.api.models.auth;

import lombok.Data;

@Data
public class AuthRefreshRequestModel extends AuthRequestModel{
    private String refreshToken;

    public AuthRefreshRequestModel(String user, String refreshToken) {
        super(user, null);
        this.refreshToken = refreshToken;
    }
}
