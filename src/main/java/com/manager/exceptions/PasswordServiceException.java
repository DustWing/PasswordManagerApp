package com.manager.exceptions;

public class PasswordServiceException extends RuntimeException {

    public PasswordServiceException() {
        super();
    }

    public PasswordServiceException(String message) {
        super(message);
    }

    public PasswordServiceException(String message, Throwable cause) {
        super(message, cause);
    }


    public PasswordServiceException(Throwable cause) {
        super(cause);
    }
}
