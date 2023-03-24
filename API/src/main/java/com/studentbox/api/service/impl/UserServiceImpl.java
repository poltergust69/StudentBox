package com.studentbox.api.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.studentbox.api.common.CustomAuthentication;
import com.studentbox.api.entities.company.Company;
import com.studentbox.api.entities.user.User;
import com.studentbox.api.entities.user.enums.RoleType;
import com.studentbox.api.exception.NotAuthenticatedException;
import com.studentbox.api.exception.NotFoundException;
import com.studentbox.api.models.auth.AuthRefreshRequestModel;
import com.studentbox.api.models.auth.AuthRequestModel;
import com.studentbox.api.models.auth.AuthResponseModel;
import com.studentbox.api.models.company.RegisterCompanyDetails;
import com.studentbox.api.models.user.RegisterUserDetails;
import com.studentbox.api.repository.UserRepository;
import com.studentbox.api.service.AuthService;
import com.studentbox.api.service.RoleService;
import com.studentbox.api.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.UUID;

import static com.studentbox.api.utils.containers.ExceptionMessageContainer.USER_NOT_FOUND_EXCEPTION_MESSAGE;
import static com.studentbox.api.utils.containers.LoggerMessageContainer.ADMIN_USER_ADDED_MESSAGE;
import static com.studentbox.api.utils.containers.LoggerMessageContainer.ADMIN_USER_NOT_ADDED_MESSAGE;
import static com.studentbox.api.utils.validators.CompanyDetailsValidator.validateCompanyDetails;
import static com.studentbox.api.utils.validators.UserDetailsValidator.validateUserDetails;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AuthService authService;
    private final RoleService roleService;

    private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    public UserServiceImpl(UserRepository userRepository, AuthService authService, RoleService roleService) {
        this.userRepository = userRepository;
        this.authService = authService;
        this.roleService = roleService;
    }

    @Value("${initialuser.username}")
    private String userUsername;

    @Value("${initialuser.email}")
    private String userEmail;

    @Value("${initialuser.password}")
    private String userPassword;

    @PostConstruct
    private void initialSetUp(){
        if(userRepository.findUserByUsernameOrEmail(userUsername, userEmail).isPresent()){
            logger.warn(ADMIN_USER_NOT_ADDED_MESSAGE);
        }
        else{
            RegisterUserDetails userDetails = new RegisterUserDetails(userUsername, userEmail, userPassword, null);
            registerUser(userDetails, RoleType.ADMIN);
            logger.debug(String.format(ADMIN_USER_ADDED_MESSAGE, userUsername));
        }
    }

    @Override
    public User findById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(
                        () -> new NotFoundException(String.format(USER_NOT_FOUND_EXCEPTION_MESSAGE, id))
                );
    }

    @Override
    public User registerUser(RegisterUserDetails details, RoleType roleType) {
        validateUserDetails(details);

        User user = new User();

        user.setId(UUID.randomUUID());
        user.setAvatarUrl(details.getAvatarUrl());
        user.setEmail(details.getEmail());
        user.setUsername(details.getUsername());
        user.setRole(roleService.getRoleByName(roleType.name()));

        user.setPassword(authService.encodePassword(details.getPassword()));

        return userRepository.save(user);
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

    @Override
    public User findAuthenticatedUser() {
        CustomAuthentication authentication = CustomAuthentication.getAuthentication();
        return userRepository.findUserByUsername((String) authentication.getPrincipal()).orElseThrow(NotAuthenticatedException::new);
    }

}
