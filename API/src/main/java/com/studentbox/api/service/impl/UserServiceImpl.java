package com.studentbox.api.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.studentbox.api.config.SecurityConfig;
import com.studentbox.api.entities.user.User;
import com.studentbox.api.exception.NotAuthenticatedException;
import com.studentbox.api.exception.NotFoundException;
import com.studentbox.api.models.auth.AuthRefreshRequestModel;
import com.studentbox.api.models.auth.AuthRequestModel;
import com.studentbox.api.models.auth.AuthResponseModel;
import com.studentbox.api.models.user.RegisterUserDetails;
import com.studentbox.api.models.user.UserDetailsModel;
import com.studentbox.api.repository.UserRepository;
import com.studentbox.api.service.AuthService;
import com.studentbox.api.service.RoleService;
import com.studentbox.api.service.UserService;
import com.studentbox.api.utils.containers.ConstantsContainer;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

import static com.studentbox.api.utils.containers.ConstantsContainer.ACCESS_TOKEN_VALID_FOR_MILLISECONDS;
import static com.studentbox.api.utils.containers.ExceptionMessageContainer.USER_NOT_FOUND_EXCEPTION_MESSAGE;
import static com.studentbox.api.utils.validators.UserDetailsValidator.validateUserDetails;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AuthService authService;
    private final RoleService roleService;

    @Override
    public User findById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(
                        () -> new NotFoundException(String.format(USER_NOT_FOUND_EXCEPTION_MESSAGE, id))
                );
    }

    @Override
    public void registerUser(RegisterUserDetails details) {
        validateUserDetails(details);

        User user = new User();

        user.setId(UUID.randomUUID());
        user.setAvatarUrl(details.getAvatarUrl());
        user.setEmail(details.getEmail());
        user.setUsername(details.getUsername());
        user.setRole(roleService.getRoleByName(details.getRole()));
        user.setPassword(authService.encodePassword(details.getPassword()));

        userRepository.save(user);
    }

    @Override
    public AuthResponseModel login(AuthRequestModel authRequestModel) throws JsonProcessingException {
        User user = userRepository.findUserByUsernameOrEmail(
                authRequestModel.getUser(),
                authRequestModel.getUser()
        ).orElseThrow(NotAuthenticatedException::new);

        return authService.login(user, authRequestModel);
    }


    @Override
    public AuthResponseModel refreshToken(AuthRefreshRequestModel authRefreshRequestModel) throws JsonProcessingException {
        User user = userRepository.findUserByUsernameOrEmail(
                authRefreshRequestModel.getUser(),
                authRefreshRequestModel.getPassword()
        ).orElseThrow(NotAuthenticatedException::new);

        return authService.refreshToken(user, authRefreshRequestModel.getRefreshToken());
    }
}
