package com.studentbox.api.utils.validators;

import com.studentbox.api.exception.NotValidException;
import com.studentbox.api.models.user.RegisterUserDetails;
import com.studentbox.api.models.user.UserDetailsModel;

import java.util.regex.Matcher;

import static com.studentbox.api.utils.containers.ConstantsContainer.*;
import static com.studentbox.api.utils.containers.ExceptionMessageContainer.*;
import static java.util.Objects.isNull;

public class UserDetailsValidator {
    public static void validateUserDetails(RegisterUserDetails userDetails){
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
    }

    public static boolean isUsernameSymbolsInvalid(String username) {
        if (isNull(username))
            return true;
        Matcher specialCharacters = SPECIAL_CHARACTER_PATTERN.matcher(username);
        Matcher usernameCharacters = USERNAME_PATTERN.matcher(username);

        if(username.contains("_") || username.contains("-") || username.contains("."))
            return !usernameCharacters.matches() || !specialCharacters.matches();
        else
            return !usernameCharacters.matches();
    }

    public static boolean isEmailInvalid(String email){
        Matcher emailMatcher = EMAIL_PATTERN.matcher(email);
        return !emailMatcher.matches();
    }

    public static boolean isPasswordInvalid(String password){
        Matcher passwordMatcher = PASSWORD_PATTERN.matcher(password);
        return !passwordMatcher.matches();
    }

    public static boolean isAvatarUrlInvalid(String avatarUrl){
        Matcher avatarMatcher = URL_PATTERN.matcher(avatarUrl);
        return !avatarMatcher.matches();
    }
}
