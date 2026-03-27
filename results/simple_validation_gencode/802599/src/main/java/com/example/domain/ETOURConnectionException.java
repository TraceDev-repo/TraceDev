package com.example.domain;

/**
 * Exception for ETOUR server connection errors.
 */
public class ETOURConnectionException extends DataAccessException {
    public ETOURConnectionException(String message, Throwable cause) {
        super(message, cause);
    }

    public ETOURConnectionException(String errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }
}