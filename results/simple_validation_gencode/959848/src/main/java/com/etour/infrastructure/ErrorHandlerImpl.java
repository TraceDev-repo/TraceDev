package com.etour.infrastructure;

import java.util.Map;
import java.util.HashMap;

/**
 * Implementation of ErrorHandler that uses NotificationService.
 */
public class ErrorHandlerImpl implements ErrorHandler {
    private NotificationService notificationService;
    private ErroredUseCaseHandler erroredUseCaseHandler;
    
    public ErrorHandlerImpl(NotificationService notificationService, ErroredUseCaseHandler erroredUseCaseHandler) {
        this.notificationService = notificationService;
        this.erroredUseCaseHandler = erroredUseCaseHandler;
    }
    
    @Override
    public void handleError(String errorType, String message) {
        System.err.println("ERROR [" + errorType + "]: " + message);
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("errorType", errorType);
        errorDetails.put("message", message);
        errorDetails.put("timestamp", new java.util.Date());
        
        // Always invoke ErroredUseCaseHandler for all error types
        erroredUseCaseHandler.handleErroredUseCase(errorType, errorDetails);
        
        // For specific error types, we might want additional handling
        if ("INVALID_IMAGE".equals(errorType)) {
            // Additional handling for image validation failure as per sequence diagram
            notifyUser("operator", "Image validation failed. Please select a valid image.");
        }
    }
    
    @Override
    public void notifyUser(String userId, String message) {
        notificationService.sendNotification(userId, message, "ERROR");
    }
}