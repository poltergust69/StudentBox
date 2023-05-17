package com.studentbox.api.service.user.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.studentbox.api.common.CustomAuthentication;
import com.studentbox.api.entities.company.Company;
import com.studentbox.api.entities.student.Student;
import com.studentbox.api.entities.user.User;
import com.studentbox.api.entities.user.enums.RoleType;
import com.studentbox.api.entities.user.forgotpassword.ForgotPasswordRequest;
import com.studentbox.api.exception.NotAuthenticatedException;
import com.studentbox.api.exception.NotFoundException;
import com.studentbox.api.exception.NotValidException;
import com.studentbox.api.models.auth.AuthRequestModel;
import com.studentbox.api.models.auth.AuthResponseModel;
import com.studentbox.api.models.sendgrid.ResetPasswordModel;
import com.studentbox.api.models.user.RegisterUserDetails;
import com.studentbox.api.repository.company.CompanyRepository;
import com.studentbox.api.repository.user.ForgotPasswordRequestRepository;
import com.studentbox.api.repository.student.StudentRepository;
import com.studentbox.api.repository.user.UserRepository;
import com.studentbox.api.service.auth.AuthService;
import com.studentbox.api.service.user.RoleService;
import com.studentbox.api.service.user.SendGridService;
import com.studentbox.api.service.user.UserService;
import com.studentbox.api.utils.containers.SharedMethodContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.UUID;

import static com.studentbox.api.utils.containers.ConstantsContainer.COMPANY_ROLE;
import static com.studentbox.api.utils.containers.ConstantsContainer.STUDENT_ROLE;
import static com.studentbox.api.utils.containers.ExceptionMessageContainer.*;
import static com.studentbox.api.utils.containers.LoggerMessageContainer.ADMIN_USER_ADDED_MESSAGE;
import static com.studentbox.api.utils.containers.LoggerMessageContainer.ADMIN_USER_NOT_ADDED_MESSAGE;
import static com.studentbox.api.utils.validators.UserDetailsValidator.validateUserDetails;
import static com.studentbox.api.utils.validators.UserDetailsValidator.validateUserPassword;
import static java.util.Objects.isNull;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final CompanyRepository companyRepository;
    private final AuthService authService;
    private final RoleService roleService;
    private final SendGridService sendGridService;
    private final ForgotPasswordRequestRepository forgotPasswordRequestRepository;
    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    public UserServiceImpl(UserRepository userRepository, StudentRepository studentRepository, CompanyRepository companyRepository, AuthService authService, RoleService roleService, SendGridService sendGridService, ForgotPasswordRequestRepository forgotPasswordRequestRepository) {
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
        this.companyRepository = companyRepository;
        this.authService = authService;
        this.roleService = roleService;
        this.sendGridService = sendGridService;
        this.forgotPasswordRequestRepository = forgotPasswordRequestRepository;
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

        if(user.getRole().getAuthority().equals(STUDENT_ROLE)){
            Student student = studentRepository.findById(user.getId()).orElseThrow(
                    () -> new NotFoundException(String.format(STUDENT_NOT_FOUND_EXCEPTION_MESSAGE, user.getUsername()))
            );
            return authService.login(user, student, authRequestModel);
        }
        else if(user.getRole().getAuthority().equals(COMPANY_ROLE)){
            Company company = companyRepository.findById(user.getId()).orElseThrow(
                    () -> new NotFoundException(String.format(COMPANY_NOT_FOUND_EXCEPTION_MESSAGE, user.getUsername()))
            );
            return authService.login(user, company, authRequestModel);
        }
        else{
            return authService.login(user, authRequestModel);
        }
    }

    @Override
    public AuthResponseModel refreshToken(String refreshToken) throws JsonProcessingException {
        UUID usernameFromToken = SharedMethodContainer.extractUserIdFromToken(refreshToken);

        if(isNull(usernameFromToken)){
            throw new NotAuthenticatedException();
        }

        User user = userRepository.findById(usernameFromToken)
                .orElseThrow(NotAuthenticatedException::new);

        return authService.refreshToken(user, refreshToken);
    }

    @Override
    public User findAuthenticatedUser() {
        CustomAuthentication authentication = CustomAuthentication.getAuthentication();

        return userRepository.findById(UUID.fromString(authentication.getPrincipal().toString()))
                .orElseThrow(() -> new NotFoundException(String.format(USER_NOT_FOUND_EXCEPTION_MESSAGE, authentication.getPrincipal().toString())));
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findUserByEmail(email)
                .orElseThrow(() -> new NotFoundException(String.format(USER_NOT_FOUND_EMAIL_EXCEPTION_MESSAGE, email)));
    }

    @Override
    public void requestForgotPasswordCode(String email) {
        User user = findByEmail(email);

        forgotPasswordRequestRepository.deleteById(user.getId());
        ForgotPasswordRequest forgotPasswordRequest = new ForgotPasswordRequest(user);
        forgotPasswordRequestRepository.save(forgotPasswordRequest);

        sendGridService.sendForgotPasswordEmail(email, user.getUsername(), forgotPasswordRequest.getSecret());
    }

    @Override
    public void resetPassword(ResetPasswordModel resetPasswordModel) {
        User user = findByEmail(resetPasswordModel.getEmail());

        ForgotPasswordRequest request = forgotPasswordRequestRepository.findByUser(user).orElseThrow(
                () -> new NotValidException(SECRET_KEY_RESET_NOT_VALID_EXCEPTION_MESSAGE));

        LocalDateTime expiresAt = request.getCreatedAt().toLocalDateTime().plusHours(1L);
        LocalDateTime currentTime = LocalDateTime.now();

        if(!request.getSecret().equals(resetPasswordModel.getSecretKey())
            || currentTime.isAfter(expiresAt)){
            throw new NotValidException(SECRET_KEY_RESET_NOT_VALID_EXCEPTION_MESSAGE);
        }

        validateUserPassword(resetPasswordModel.getPassword());

        String newPassword = authService.encodePassword(resetPasswordModel.getPassword());
        user.setPassword(newPassword);

        userRepository.save(user);
        forgotPasswordRequestRepository.delete(request);
    }
}
