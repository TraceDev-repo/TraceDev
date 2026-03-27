package com.newsagency.model;

import com.newsagency.dto.NewsDTO;
import java.util.List;

/**
 * Holds the result of a validation operation.
 */
public class ValidationResult {

    private boolean valid;
    private List<String> errorMessages;
    private NewsDTO validatedData;

    public ValidationResult() {
    }

    public ValidationResult(boolean valid, List<String> errorMessages, NewsDTO validatedData) {
        this.valid = valid;
        this.errorMessages = errorMessages;
        this.validatedData = validatedData;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public List<String> getErrorMessages() {
        return errorMessages;
    }

    public void setErrorMessages(List<String> errorMessages) {
        this.errorMessages = errorMessages;
    }

    public NewsDTO getValidatedData() {
        return validatedData;
    }

    public void setValidatedData(NewsDTO validatedData) {
        this.validatedData = validatedData;
    }
}