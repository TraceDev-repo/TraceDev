package com.etour.infrastructure;

import java.util.Map;

/**
 * Handles logging and processing of errored use cases.
 * Added to satisfy quality requirement for Errored use case handling.
 */
public class ErroredUseCaseHandler {
    private ErrorHandler errorHandler;
    
    public ErroredUseCaseHandler(ErrorHandler errorHandler) {
        this.errorHandler = errorHandler;
    }
    
    public void handleErroredUseCase(String errorType, Map<String, Object> errorDetails) {
        System.err.println("ErroredUseCaseHandler processing error type: " + errorType);
        logErroredCase(errorDetails);
        // Optionally notify an admin or system monitor
        errorHandler.notifyUser("admin", "Errored use case: " + errorType);
    }
    
    public void logErroredCase(Map<String, Object> caseData) {
        System.err.println("LOG Errored Case: " + caseData);
        // In a real system, this would write to a log file or database.
    }
}