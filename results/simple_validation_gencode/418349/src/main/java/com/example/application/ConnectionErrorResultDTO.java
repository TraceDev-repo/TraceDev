package com.example.application;

/**
 * DTO for connection error results.
 */
public class ConnectionErrorResultDTO extends SearchResultDTO {
    private final String errorMessage;

    public ConnectionErrorResultDTO(String errorMessage) {
        super(java.util.Collections.emptyList(), 0, 0);
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public String toString() {
        return "ConnectionErrorResultDTO{" +
                "errorMessage='" + errorMessage + '\'' +
                '}';
    }
}