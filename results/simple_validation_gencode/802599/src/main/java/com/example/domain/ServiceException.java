package com.example.domain;

/**
 * Base exception for service layer errors.
 */
public class ServiceException extends RuntimeException {
    private String errorCode;
    private String message;
    private Throwable cause;

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
        this.cause = cause;
    }

    public ServiceException(String errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
        this.message = message;
        this.cause = cause;
    }

    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Throwable getCause() {
        return cause;
    }
}