package com.studentbox.api.service.auth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.studentbox.api.entities.company.Company;
import com.studentbox.api.entities.student.Student;
import com.studentbox.api.entities.user.User;
import com.studentbox.api.models.auth.AuthRequestModel;
import com.studentbox.api.models.auth.AuthResponseModel;

public interface AuthService {
    AuthResponseModel login(User user, AuthRequestModel requestModel) throws JsonProcessingException;
    AuthResponseModel login(User user, Company company, AuthRequestModel requestModel) throws JsonProcessingException;
    AuthResponseModel login(User user, Student student, AuthRequestModel requestModel) throws JsonProcessingException;
    AuthResponseModel refreshToken(User user, String refreshToken) throws JsonProcessingException;
    String encodePassword(String password);
}
