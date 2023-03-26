package com.studentbox.api.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import static com.studentbox.api.utils.containers.ExceptionMessageContainer.USER_NOT_AUTHENTICATED_EXCEPTION_MESSAGE;

public class NotAuthenticatedException extends ResponseStatusException{
    public NotAuthenticatedException() {
        super(HttpStatus.FORBIDDEN, USER_NOT_AUTHENTICATED_EXCEPTION_MESSAGE);
    }
}
