package com.agency;

/**
 * Exception thrown when a connection error occurs.
 */
public class ConnectionException extends RuntimeException {
    public ConnectionException(String message) {
        super(message);
    }
}