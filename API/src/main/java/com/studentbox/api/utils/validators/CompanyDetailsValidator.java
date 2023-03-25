package com.studentbox.api.utils.validators;

import com.studentbox.api.exception.NotValidException;
import com.studentbox.api.models.company.RegisterCompanyDetails;

import java.util.regex.Matcher;

import static com.studentbox.api.utils.containers.ConstantsContainer.*;
import static com.studentbox.api.utils.containers.ExceptionMessageContainer.*;
import static com.studentbox.api.utils.containers.ExceptionMessageContainer.NOT_VALID_AVATAR_URL_EXCEPTION_MESSAGE;
import static java.util.Objects.isNull;

public class CompanyDetailsValidator {
    private CompanyDetailsValidator(){
        throw new IllegalStateException(UTILITY_CLASS_INITIALIZED_EXCEPTION_MESSAGE);
    }

    public static void validateCompanyDetails(RegisterCompanyDetails companyDetails){
        if(isUsernameSymbolsInvalid(companyDetails.getUsername())){
            throw new NotValidException(String.format(NOT_VALID_USERNAME_EXCEPTION_MESSAGE, companyDetails.getUsername()));
        }

        if(isEmailInvalid(companyDetails.getEmail())){
            throw new NotValidException(String.format(NOT_VALID_EMAIL_EXCEPTION_MESSAGE, companyDetails.getEmail()));
        }

        if(isPasswordInvalid(companyDetails.getPassword())){
            throw new NotValidException(NOT_VALID_PASSWORD_EXCEPTION_MESSAGE);
        }

        if(isAvatarUrlInvalid(companyDetails.getAvatarUrl())){
            throw new NotValidException(NOT_VALID_AVATAR_URL_EXCEPTION_MESSAGE);
        }

        if(isNameInvalid(companyDetails.getName())){
            throw new NotValidException(NOT_VALID_COMPANY_NAME_EXCEPTION_MESSAGE);
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

    public static boolean isNameInvalid(String name){
        return !isNull(name) && !name.isBlank();
    }
}
