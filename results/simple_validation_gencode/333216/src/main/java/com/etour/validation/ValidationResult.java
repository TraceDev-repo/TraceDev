package com.etour.validation;

import java.util.List;

/**
 * Holds the result of a validation, including status and error messages.
 */
public class ValidationResult {
    private boolean isValid;
    private List<String> errors;

    public ValidationResult(boolean isValid, List<String> errors) {
        this.isValid = isValid;
        this.errors = errors;
    }

    public boolean getIsValid() {
        return isValid;
    }

    public List<String> getErrors() {
        return errors;
    }
}