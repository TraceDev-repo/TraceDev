package com.example.validation;

import java.util.List;

/**
 * Value Object that contains validation results.
 */
public class ValidationResult {
    private boolean valid;
    private List<String> errorMessages;

    // Constructors
    public ValidationResult() {}

    public ValidationResult(boolean valid, List<String> errorMessages) {
        this.valid = valid;
        this.errorMessages = errorMessages;
    }

    // Getters and Setters
    public boolean getIsValid() {
        return valid;
    }

    public void setIsValid(boolean valid) {
        this.valid = valid;
    }

    public List<String> getErrorMessages() {
        return errorMessages;
    }

    public void setErrorMessages(List<String> errorMessages) {
        this.errorMessages = errorMessages;
    }
}