package com.example.exception;

import java.util.Date;

/**
 * Exception that encapsulates ETOUR server connection errors.
 */
public class ConnectionException extends Exception {
    private String errorCode;
    private Date timestamp;

    // Constructors
    public ConnectionException() {
        this.timestamp = new Date();
    }

    public ConnectionException(String message) {
        super(message);
        this.timestamp = new Date();
    }

    public ConnectionException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.timestamp = new Date();
    }

    // Getters and Setters
    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}