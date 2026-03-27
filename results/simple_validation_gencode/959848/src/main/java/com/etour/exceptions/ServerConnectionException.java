package com.etour.exceptions;

import java.util.Date;

/**
 * Exception thrown when a server connection fails.
 */
public class ServerConnectionException extends RuntimeException {
    private String errorCode;
    private Date timestamp;
    
    public ServerConnectionException(String errorCode) {
        super("Server connection failed with error code: " + errorCode);
        this.errorCode = errorCode;
        this.timestamp = new Date();
    }
    
    public ServerConnectionException(String errorCode, String message) {
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