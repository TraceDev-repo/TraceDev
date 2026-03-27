package com.example.infrastructure;

/**
 * Exception thrown when connection to the eTour server is interrupted.
 */
public class ConnectionException extends Exception {
    public ConnectionException(String message) {
        super(message);
    }

    public ConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}