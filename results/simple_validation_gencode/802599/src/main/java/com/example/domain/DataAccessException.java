package com.example.domain;

/**
 * Exception for data access layer errors.
 */
public class DataAccessException extends ServiceException {
    public DataAccessException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataAccessException(String errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }
}