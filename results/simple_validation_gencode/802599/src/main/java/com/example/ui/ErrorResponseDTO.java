package com.example.ui;

import java.time.LocalDateTime;

/**
 * Error response DTO for presenting errors to the user.
 */
public class ErrorResponseDTO {
    private String errorCode;
    private String message;
    private LocalDateTime timestamp;

    public ErrorResponseDTO() {
        this.timestamp = LocalDateTime.now();
    }

    public ErrorResponseDTO(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Returns a formatted error string.
     */
    public String getFormattedError() {
        return String.format("[%s] %s (at %s)", errorCode, message, timestamp);
    }
}