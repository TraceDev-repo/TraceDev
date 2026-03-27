package com.example.web.result;

/**
 * Result of a save/insert operation.
 */
public class OperationResult {
    private boolean success;
    private String message;

    private OperationResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public static OperationResult success(String message) {
        return new OperationResult(true, message);
    }

    public static OperationResult failure(String message) {
        return new OperationResult(false, message);
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}