package com.example.application.validation;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the result of a validation operation.
 * Contains validation status and error messages.
 */
public class ValidationResult {
    private boolean isValid;
    private List<String> errorMessages;

    /**
     * Default constructor - creates a valid result.
     */
    public ValidationResult() {
        this.isValid = true;
        this.errorMessages = new ArrayList<>();
    }

    /**
     * Constructor with explicit values.
     * @param isValid whether the validation passed
     * @param messages list of error messages
     */
    public ValidationResult(boolean isValid, List<String> messages) {
        this.isValid = isValid;
        this.errorMessages = messages != null ? messages : new ArrayList<>();
    }

    /**
     * Adds an error message and marks validation as invalid.
     * @param message the error message to add
     */
    public void addErrorMessage(String message) {
        this.errorMessages.add(message);
        this.isValid = false;
    }

    public boolean getIsValid() {
        return isValid;
    }

    public List<String> getErrorMessages() {
        return errorMessages;
    }
}