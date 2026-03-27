package com.example.web.result;

import com.example.web.form.ConfirmationRequest;

/**
 * Result of the validation step; contains a confirmation request if validation passes.
 */
public class ValidationResult {
    private boolean valid;
    private String message;
    private ConfirmationRequest confirmationRequest;

    private ValidationResult(boolean valid, String message, ConfirmationRequest confirmationRequest) {
        this.valid = valid;
        this.message = message;
        this.confirmationRequest = confirmationRequest;
    }

    public static ValidationResult success(ConfirmationRequest confirmationRequest) {
        return new ValidationResult(true, "Validation passed.", confirmationRequest);
    }

    public static ValidationResult failure(String errorMessage) {
        return new ValidationResult(false, errorMessage, null);
    }

    public boolean isValid() {
        return valid;
    }

    public String getMessage() {
        return message;
    }

    public ConfirmationRequest getConfirmationRequest() {
        return confirmationRequest;
    }
}