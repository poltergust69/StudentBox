package com.studentbox.api.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NotAuthenticatedException extends ResponseStatusException{
    public NotAuthenticatedException() {
        super(HttpStatus.FORBIDDEN, "User failed to be authenticated.");
    }
}
