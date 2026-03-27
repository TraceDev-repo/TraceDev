package com.etour.exceptions;

import java.util.Date;

/**
 * Exception thrown when authentication fails.
 */
public class AuthenticationException extends RuntimeException {
    private String errorCode;
    private Date timestamp;
    
    public AuthenticationException(String errorCode) {
        super("Authentication failed with error code: " + errorCode);
        this.errorCode = errorCode;
        this.timestamp = new Date();
    }
    
    public AuthenticationException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.timestamp = new Date();
    }
    
    public String getErrorCode() {
        return errorCode;
    }
    
    public Date getTimestamp() {
        return timestamp;
    }
}