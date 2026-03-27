package com.example.exception;

/**
 * Custom exception representing a connection interruption to the server.
 * As per class diagram and sequence diagram.
 */
public class ETourConnectionException extends Exception {
    private String message;

    public ETourConnectionException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}