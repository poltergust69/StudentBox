package com.studentbox.api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.studentbox.api.entities.user.User;
import com.studentbox.api.models.auth.AuthRefreshRequestModel;
import com.studentbox.api.models.auth.AuthRequestModel;
import com.studentbox.api.models.auth.AuthResponseModel;
import com.studentbox.api.models.user.RegisterUserDetails;

import java.util.UUID;

public interface UserService {
    public User findById(UUID id);
    public AuthResponseModel login(AuthRequestModel authRequestModel) throws JsonProcessingException;
    public void registerUser(RegisterUserDetails details);
    public AuthResponseModel refreshToken(AuthRefreshRequestModel authRefreshRequestModel) throws JsonProcessingException;
}
