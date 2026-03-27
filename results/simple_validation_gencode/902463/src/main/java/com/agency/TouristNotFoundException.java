package com.agency;

/**
 * Exception thrown when a tourist is not found.
 */
public class TouristNotFoundException extends RuntimeException {
    public TouristNotFoundException(String message) {
        super(message);
    }
}