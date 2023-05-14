package com.studentbox.api.service.auth.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.studentbox.api.config.SecurityConfig;
import com.studentbox.api.entities.company.Company;
import com.studentbox.api.entities.student.Student;
import com.studentbox.api.entities.user.User;
import com.studentbox.api.exception.NotAuthenticatedException;
import com.studentbox.api.models.auth.AuthRequestModel;
import com.studentbox.api.models.auth.AuthResponseModel;
import com.studentbox.api.service.auth.AuthService;
import com.studentbox.api.utils.mappers.CompanyMapper;
import com.studentbox.api.utils.mappers.StudentMapper;
import com.studentbox.api.utils.mappers.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

import static com.studentbox.api.utils.containers.ConstantsContainer.ACCESS_TOKEN_VALID_FOR_MILLISECONDS;
import static com.studentbox.api.utils.containers.ConstantsContainer.REFRESH_TOKEN_VALID_FOR_MILLISECONDS;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final SecurityConfig securityConfig;
    private final PasswordEncoder passwordEncoder;
    @Override
    public AuthResponseModel login(User user, AuthRequestModel requestModel) throws JsonProcessingException {
        if(!passwordEncoder.matches(requestModel.getPassword(), user.getPassword())){
            throw new NotAuthenticatedException();
        }

        String accessToken = generateAccessToken(user);
        String refreshToken = generateRefreshToken(user);

        return new AuthResponseModel(accessToken, refreshToken);
    }

    @Override
    public AuthResponseModel login(User user, Company company, AuthRequestModel requestModel) throws JsonProcessingException {
        if(!passwordEncoder.matches(requestModel.getPassword(), user.getPassword())){
            throw new NotAuthenticatedException();
        }

        String accessToken = generateAccessToken(company);
        String refreshToken = generateRefreshToken(user);

        return new AuthResponseModel(accessToken, refreshToken);
    }

    @Override
    public AuthResponseModel login(User user, Student student, AuthRequestModel requestModel) throws JsonProcessingException {
        if(!passwordEncoder.matches(requestModel.getPassword(), user.getPassword())){
            throw new NotAuthenticatedException();
        }

        String accessToken = generateAccessToken(student);
        String refreshToken = generateRefreshToken(user);

        return new AuthResponseModel(accessToken, refreshToken);
    }

    @Override
    public AuthResponseModel refreshToken(User user, String refreshToken) throws JsonProcessingException {
        String accessToken = generateAccessToken(user);

        return new AuthResponseModel(accessToken, refreshToken);
    }

    @Override
    public AuthResponseModel refreshToken(Company company, String refreshToken) throws JsonProcessingException {
        String accessToken = generateAccessToken(company);

        return new AuthResponseModel(accessToken, refreshToken);
    }

    @Override
    public AuthResponseModel refreshToken(Student student, String refreshToken) throws JsonProcessingException {
        String accessToken = generateAccessToken(student);

        return new AuthResponseModel(accessToken, refreshToken);
    }

    @Override
    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    private String generateAccessToken(User user) throws JsonProcessingException {
        return JWT.create()
                .withSubject(new ObjectMapper().writeValueAsString(UserMapper.mapToDetailsModel(user)))
                .withExpiresAt(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALID_FOR_MILLISECONDS))
                .sign(Algorithm.HMAC256(securityConfig.getJwtSecretKey()));
    }

    private String generateAccessToken(Company company) throws JsonProcessingException {
        return JWT.create()
                .withSubject(new ObjectMapper().writeValueAsString(CompanyMapper.mapToDetailsModel(company)))
                .withExpiresAt(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALID_FOR_MILLISECONDS))
                .sign(Algorithm.HMAC256(securityConfig.getJwtSecretKey()));
    }

    private String generateAccessToken(Student student) throws JsonProcessingException {
        return JWT.create()
                .withSubject(new ObjectMapper().writeValueAsString(StudentMapper.mapToDetailsModel(student)))
                .withExpiresAt(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALID_FOR_MILLISECONDS))
                .sign(Algorithm.HMAC256(securityConfig.getJwtSecretKey()));
    }

    private String generateRefreshToken(User user) throws JsonProcessingException {
        return JWT.create()
                .withSubject(new ObjectMapper().writeValueAsString(UserMapper.mapToDetailsModel(user)))
                .withExpiresAt(new Date(System.currentTimeMillis() + REFRESH_TOKEN_VALID_FOR_MILLISECONDS))
                .sign(Algorithm.HMAC512(securityConfig.getJwtRefreshSecretKey()));
    }
}
