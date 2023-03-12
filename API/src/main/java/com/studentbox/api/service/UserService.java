package com.studentbox.api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.studentbox.api.entities.user.User;
import com.studentbox.api.entities.user.enums.RoleType;
import com.studentbox.api.models.auth.AuthRefreshRequestModel;
import com.studentbox.api.models.auth.AuthRequestModel;
import com.studentbox.api.models.auth.AuthResponseModel;
import com.studentbox.api.models.user.RegisterUserDetails;

import java.util.UUID;

public interface UserService {
    User findById(UUID id);
    AuthResponseModel login(AuthRequestModel authRequestModel) throws JsonProcessingException;
    void registerUser(RegisterUserDetails details, RoleType roleType);
    AuthResponseModel refreshToken(AuthRefreshRequestModel authRefreshRequestModel) throws JsonProcessingException;
    User findAuthenticatedUser();
}
