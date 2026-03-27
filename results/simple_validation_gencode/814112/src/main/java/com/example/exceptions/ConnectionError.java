package com.example.exceptions;

/**
 * Exception thrown when a connection error occurs.
 */
public class ConnectionError extends RuntimeException {
    public ConnectionError(String message) {
        super(message);
    }
}