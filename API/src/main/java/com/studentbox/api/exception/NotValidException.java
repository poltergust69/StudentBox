package com.studentbox.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NotValidException extends ResponseStatusException {
    public NotValidException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
