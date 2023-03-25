package com.studentbox.api.utils.containers;

import static com.studentbox.api.utils.containers.ExceptionMessageContainer.UTILITY_CLASS_INITIALIZED_EXCEPTION_MESSAGE;

public class LoggerMessageContainer {
    private LoggerMessageContainer(){
        throw new IllegalStateException(UTILITY_CLASS_INITIALIZED_EXCEPTION_MESSAGE);
    }
    public static final String ADMIN_USER_NOT_ADDED_MESSAGE = "Admin user already existed and was not created";
    public static final String ADMIN_USER_ADDED_MESSAGE = "Admin user with username \"%s\" was successfully created!";
    public static final String SENDGRID_EMAIL_RESULT_MESSAGE = "SendGrid sending of E-Mail to user \"%s\" was %s SENT";
}
