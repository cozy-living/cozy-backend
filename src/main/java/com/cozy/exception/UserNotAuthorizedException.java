package com.cozy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class UserNotAuthorizedException extends RuntimeException {
    public UserNotAuthorizedException() {
        super();
    }

    public UserNotAuthorizedException(String message) {
        super(message);
    }

    public UserNotAuthorizedException(String message, Throwable cause) {
        super(message, cause);
    }
}