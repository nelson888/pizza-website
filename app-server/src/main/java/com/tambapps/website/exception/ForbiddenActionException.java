package com.tambapps.website.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForbiddenActionException extends RuntimeException {

    public ForbiddenActionException(String message, Throwable cause) {
        super(message, cause);
    }

    public ForbiddenActionException(String message) {
        super(message);
    }
}
