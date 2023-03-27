package com.studentbox.api.service.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.studentbox.api.entities.user.User;
import com.studentbox.api.entities.user.enums.RoleType;
import com.studentbox.api.models.auth.AuthRefreshRequestModel;
import com.studentbox.api.models.auth.AuthRequestModel;
import com.studentbox.api.models.auth.AuthResponseModel;
import com.studentbox.api.models.sendgrid.ResetPasswordModel;
import com.studentbox.api.models.user.RegisterUserDetails;

import java.util.UUID;

public interface UserService {
    User findById(UUID id);
    AuthResponseModel login(AuthRequestModel authRequestModel) throws JsonProcessingException;
    User registerUser(RegisterUserDetails details, RoleType roleType);
    AuthResponseModel refreshToken(String refreshToken) throws JsonProcessingException;
    User findAuthenticatedUser();
    User findByEmail(String email);
    void requestForgotPasswordCode(String email);
    void resetPassword(ResetPasswordModel resetPasswordModel);
}
