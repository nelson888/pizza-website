package com.tambapps.website.exception;

public class DBFileStorageException extends RuntimeException {
  public DBFileStorageException(String message) {
    super(message);
  }

  public DBFileStorageException(String message, Throwable cause) {
    super(message, cause);
  }
}
