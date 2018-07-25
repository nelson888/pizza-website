package com.tambapps.website.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DBFileNotFoundException extends RuntimeException {
  public DBFileNotFoundException(String message) {
    super(message);
  }

  public DBFileNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
