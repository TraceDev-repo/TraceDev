package com.example.application;

/**
 * DTO for timeout results.
 */
public class TimeoutResultDTO extends SearchResultDTO {
    private final String timeoutMessage;

    public TimeoutResultDTO(String timeoutMessage) {
        super(java.util.Collections.emptyList(), 0, 0);
        this.timeoutMessage = timeoutMessage;
    }

    public String getTimeoutMessage() {
        return timeoutMessage;
    }

    @Override
    public String toString() {
        return "TimeoutResultDTO{" +
                "timeoutMessage='" + timeoutMessage + '\'' +
                '}';
    }
}