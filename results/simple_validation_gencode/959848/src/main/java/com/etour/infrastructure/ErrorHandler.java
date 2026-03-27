package com.etour.infrastructure;

/**
 * Interface for error handling and user notification.
 */
public interface ErrorHandler {
    void handleError(String errorType, String message);
    void notifyUser(String userId, String message);
}