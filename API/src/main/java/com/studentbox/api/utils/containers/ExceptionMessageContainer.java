package com.studentbox.api.utils.containers;

public class ExceptionMessageContainer {
    public static final String USER_NOT_FOUND_EXCEPTION_MESSAGE = "The User with ID \"%s\" does not exist.";
    public static final String USER_NOT_AUTHENTICATED_EXCEPTION_MESSAGE = "User failed to be authenticated.";
    public static final String ROLE_WITH_NAME_NOT_FOUND_EXCEPTION_MESSAGE = "The role with name \"%s\" does not exist.";
    public static final String NOT_VALID_USERNAME_EXCEPTION_MESSAGE = "The username \"%s\" is not valid. Please make sure that your username has between 6 and 50 characters, and keep in mind that only numbers, lowercase letters  and the symbols '_', '-' and '.' are allowed.";
    public static final String NOT_VALID_EMAIL_EXCEPTION_MESSAGE = "The E-Mail address \"%s\" is not valid.";
    public static final String NOT_VALID_PASSWORD_EXCEPTION_MESSAGE =  "The password that you provided is not valid.";
    public static final String NOT_VALID_AVATAR_URL_EXCEPTION_MESSAGE = "The avatar URL that you provided is not valid.";
    public static final String NOT_VALID_POST_TITLE_EXCEPTION_MESSAGE = "The title that you provided is not valid.";
    public static final String NOT_VALID_POST_CONTENT_EXCEPTION_MESSAGE = "The content of the post that you provided is not valid.";
    public static final String NOT_VALID_REPLY_CONTENT_EXCEPTION_MESSAGE = "The content of the reply that you provided is not valid.";
    public static final String POST_NOT_FOUND_EXCEPTION_MESSAGE = "The post with ID \"%s\" does not exist.";
    public static final String POST_REPLY_NOT_FOUND_EXCEPTION_MESSAGE = "The reply with ID \"%s\" does not exist.";
}
