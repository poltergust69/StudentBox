package com.studentbox.api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.studentbox.api.entities.user.User;
import com.studentbox.api.models.auth.AuthRequestModel;
import com.studentbox.api.models.auth.AuthResponseModel;

public interface AuthService {
    public AuthResponseModel login(User user, AuthRequestModel requestModel) throws JsonProcessingException;
    public AuthResponseModel refreshToken(User user, String refreshToken) throws JsonProcessingException;
    public String encodePassword(String password);
}
