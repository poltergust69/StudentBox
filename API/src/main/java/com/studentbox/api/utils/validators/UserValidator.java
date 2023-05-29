package com.studentbox.api.utils.validators;

import com.studentbox.api.exception.NotValidException;
import com.studentbox.api.models.user.RegisterUserDetails;
import com.studentbox.api.repository.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import static com.studentbox.api.utils.containers.ExceptionMessageContainer.*;
import static com.studentbox.api.utils.containers.SharedMethodContainer.*;

@Component
@AllArgsConstructor
public class UserValidator {
    private UserRepository userRepository;
    public void validateUserRegistrationDetails(RegisterUserDetails userDetails){
        if(isUsernameSymbolsInvalid(userDetails.getUsername())){
            throw new NotValidException(String.format(NOT_VALID_USERNAME_EXCEPTION_MESSAGE, userDetails.getUsername()));
        }

        if(isEmailInvalid(userDetails.getEmail())){
            throw new NotValidException(String.format(NOT_VALID_EMAIL_EXCEPTION_MESSAGE, userDetails.getEmail()));
        }

        if(isPasswordInvalid(userDetails.getPassword())){
            throw new NotValidException(NOT_VALID_PASSWORD_EXCEPTION_MESSAGE);
        }

        if(isAvatarUrlInvalid(userDetails.getAvatarUrl())){
            throw new NotValidException(NOT_VALID_AVATAR_URL_EXCEPTION_MESSAGE);
        }

        if(userRepository.findUserByUsername(userDetails.getUsername()).isPresent()){
            throw new NotValidException(String.format(USERNAME_ALREADY_TAKEN_EXCEPTION_MESSAGE, userDetails.getUsername()));
        }

        if(userRepository.findUserByEmail(userDetails.getEmail()).isPresent()){
            throw new NotValidException(String.format(EMAIL_ALREADY_TAKEN_EXCEPTION_MESSAGE, userDetails.getEmail()));
        }
    }
}
