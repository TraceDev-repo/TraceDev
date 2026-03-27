package com.etour.tourist.domain;

/**
 * Custom exception thrown when a tourist is not found.
 */
public class TouristNotFoundException extends RuntimeException {
    private String touristId;
    private String message;

    public TouristNotFoundException(String touristId, String message) {
        super(message);
        this.touristId = touristId;
        this.message = message;
    }

    public String getTouristId() {
        return touristId;
    }

    @Override
    public String getMessage() {
        return message;
    }
}