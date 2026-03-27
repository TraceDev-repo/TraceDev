package com.example.ui;

import com.example.controller.SearchTouristController;
import com.example.dto.SearchTouristDTO;
import com.example.dto.TouristAccountDTO;
import com.example.enums.TouristAccountStatus;
import com.example.exception.ConnectionException;
import com.example.service.SessionManager;
import com.example.validation.ValidationResult;

import java.util.List;
import java.util.Map;

/**
 * Boundary class that handles form rendering and user interaction.
 * This is a simplified console\u2011based UI for demonstration.
 */
public class SearchFormUI {
    private SearchTouristController controller;
    private SessionManager sessionManager;

    // Constructor
    public SearchFormUI(SearchTouristController controller, SessionManager sessionManager) {
        this.controller = controller;
        this.sessionManager = sessionManager;
    }

    /**
     * Displays the search form with default values (Step 2 of flow).
     * @param defaultValues a map of default field values
     */
    public void displayForm(Map<String, String> defaultValues) {
        System.out.println("\n=== Tourist Search Form ===");
        System.out.println("Please enter search criteria (press Enter to skip a field):");
        System.out.println("Name: " + defaultValues.getOrDefault("name", ""));
        System.out.println("Email: " + defaultValues.getOrDefault("email", ""));
        System.out.println("Country: " + defaultValues.getOrDefault("country", ""));
        System.out.println("Status (ACTIVE, INACTIVE, SUSPENDED): " + defaultValues.getOrDefault("status", ""));
        System.out.println("----------------------------------------");
    }

    /**
     * Initiates the search flow; called by the Agency Operator actor.
     * This method simulates the sequence diagram interactions.
     */
    public void startSearchFlow() {
        System.out.println("Agency Operator accesses tourist search.");

        // Entry Condition: Authentication check before form display
        String sessionId = sessionManager.getCurrentSessionId();
        if (!sessionManager.isValidSession()) {
            showError("Access denied: Invalid session.");
            return;
        }

        // Load defaults and display form
        Map<String, String> defaults = Map.of("name", "", "email", "", "country", "", "status", "ACTIVE");
        loadFormWithDefaultValues(defaults);
        displayForm(defaults);

        // Simulate the Agency Operator filling out the form (Step 3)
        SearchTouristDTO criteria = simulateFormSubmission();

        // Submit the form (Step 4)
        ValidationResult validationResult = submitSearch(criteria);
        if (!validationResult.getIsValid()) {
            showValidationErrors(validationResult);
            return;
        }

        try {
            List<TouristAccountDTO> results = controller.executeSearch(criteria);
            if (results.isEmpty()) {
                showNoResultsMessage();
            } else {
                showSearchResults(results);
            }
        } catch (SecurityException e) {
            showError("Access denied: " + e.getMessage());
        } catch (ConnectionException e) {
            showError("Connection error: " + e.getMessage());
        } catch (Exception e) {
            showError("Unexpected error: " + e.getMessage());
        }
    }

    /**
     * Simulates the UI loading default values (called from sequence diagram).
     */
    private void loadFormWithDefaultValues(Map<String, String> defaults) {
        // In a real GUI, this would populate text fields with defaults.
        System.out.println("System loads default values into the form.");
    }

    /**
     * Simulates the Agency Operator filling and submitting the form.
     * In reality, this would capture input from UI components.
     * @return a populated SearchTouristDTO
     */
    private SearchTouristDTO simulateFormSubmission() {
        // For demonstration, we hard\u2011code some sample criteria.
        SearchTouristDTO dto = new SearchTouristDTO();
        dto.setName("John");      // partial name match
        dto.setEmail("");         // empty email
        dto.setCountry("");       // empty country
        dto.setStatus(TouristAccountStatus.ACTIVE);
        return dto;
    }

    /**
     * Displays the search results (list of TouristAccountDTOs).
     * @param results the list of DTOs to display
     */
    public void showSearchResults(List<TouristAccountDTO> results) {
        System.out.println("\n=== Search Results ===");
        for (TouristAccountDTO dto : results) {
            System.out.println(dto);
        }
        System.out.println("Total records: " + results.size());
    }

    /**
     * Displays an error message to the user.
     * @param message the error message
     */
    public void showError(String message) {
        System.out.println("\n[ERROR] " + message);
    }

    /**
     * Displays a message when no results are found.
     */
    public void showNoResultsMessage() {
        System.out.println("\nNo tourist accounts match the search criteria.");
    }

    /**
     * Submits search criteria and returns validation result.
     * @param criteria the search criteria
     * @return ValidationResult containing validation outcome
     */
    public ValidationResult submitSearch(SearchTouristDTO criteria) {
        try {
            List<TouristAccountDTO> results = controller.executeSearch(criteria);
            // If no exception is thrown, validation passed
            return new ValidationResult(true, java.util.Collections.emptyList());
        } catch (IllegalArgumentException e) {
            // Extract error messages from exception (controller throws IllegalArgumentException for validation errors)
            // For better design, controller could return ValidationResult directly, but we adapt to existing code.
            return new ValidationResult(false, java.util.Arrays.asList(e.getMessage()));
        } catch (SecurityException | ConnectionException e) {
            // These are not validation errors
            throw new RuntimeException(e);
        }
    }

    /**
     * Displays validation error messages to the user.
     * @param validationResult the result containing error messages
     */
    public void showValidationErrors(ValidationResult validationResult) {
        System.out.println("\nValidation errors:");
        for (String error : validationResult.getErrorMessages()) {
            System.out.println("- " + error);
        }
    }
}