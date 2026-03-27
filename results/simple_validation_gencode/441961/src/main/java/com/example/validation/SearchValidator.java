package com.example.validation;

import com.example.dto.SearchTouristDTO;
import com.example.enums.TouristAccountStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * Service that validates search criteria before processing.
 */
public class SearchValidator {

    /**
     * Validates search criteria.
     * Assumption: name, email, and country are optional; status is optional.
     * If provided, email format is validated.
     * @param criteria the search criteria DTO
     * @return ValidationResult containing validation outcome and error messages
     */
    public ValidationResult validateSearchCriteria(SearchTouristDTO criteria) {
        List<String> errors = new ArrayList<>();

        // Validate email format if provided
        if (criteria.getEmail() != null && !criteria.getEmail().isEmpty()) {
            if (!isValidEmail(criteria.getEmail())) {
                errors.add("Invalid email format.");
            }
        }

        // Validate status if provided
        if (criteria.getStatus() != null) {
            try {
                TouristAccountStatus.valueOf(criteria.getStatus().name());
            } catch (IllegalArgumentException e) {
                errors.add("Invalid status value.");
            }
        }

        // Additional validation rules can be added here

        boolean isValid = errors.isEmpty();
        return new ValidationResult(isValid, errors);
    }

    private boolean isValidEmail(String email) {
        // Simple email validation regex for demonstration
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email.matches(emailRegex);
    }
}