package com.example.adapters.controllers;

import com.example.application.RefreshmentPointDto;
import com.example.application.SearchRefreshmentPointsQuery;
import com.example.application.SearchRefreshmentPointsUseCase;
import com.example.adapters.views.SearchForm;
import com.example.adapters.views.SearchFormValidationResult;

import java.util.List;

/**
 * Controller for handling refreshment point related requests.
 * This class belongs to the Interface Adapters Layer.
 */
public class RefreshmentPointController {
    private final SearchRefreshmentPointsUseCase useCase;

    public RefreshmentPointController(SearchRefreshmentPointsUseCase useCase) {
        this.useCase = useCase;
    }

    /**
     * Activates the search functionality (step 1 in sequence diagram).
     * This method would typically be called by the UI to start the search process.
     */
    public void activateSearch() {
        // In a real application, this might trigger UI navigation
        System.out.println("Search functionality activated.");
    }

    /**
     * Shows the search form to the user (step 2 in sequence diagram).
     *
     * @return a new SearchForm instance
     */
    public SearchForm showSearchForm() {
        SearchForm form = new SearchForm();
        // In a real application, this would be displayed on the UI
        System.out.println("Search form displayed.");
        return form;
    }

    /**
     * Submits the filled form for processing (step 4 in sequence diagram).
     *
     * @param form the filled search form
     * @return a list of refreshment point DTOs if validation succeeds, otherwise null
     */
    public List<RefreshmentPointDto> submitForm(SearchForm form) {
        // Validate the form
        SearchFormValidationResult validationResult = form.validate();
        if (!validationResult.isSuccess()) {
            showValidationErrors(validationResult.getErrors());
            return null;
        }

        // Convert form to query
        SearchRefreshmentPointsQuery query = form.toQuery();

        // Execute the use case
        List<RefreshmentPointDto> results = useCase.execute(query);

        // Display results (in a real app, this would update the UI)
        displayResults(results);
        return results;
    }

    /**
     * Displays validation errors to the user.
     *
     * @param errors the list of error messages
     */
    public void showValidationErrors(List<String> errors) {
        System.out.println("Validation errors:");
        for (String error : errors) {
            System.out.println(" - " + error);
        }
    }

    /**
     * Displays search results to the user.
     *
     * @param dtos the list of refreshment point DTOs
     */
    public void displayResults(List<RefreshmentPointDto> dtos) {
        System.out.println("Search results:");
        if (dtos == null || dtos.isEmpty()) {
            System.out.println("No refreshment points found.");
        } else {
            for (RefreshmentPointDto dto : dtos) {
                System.out.println(" - " + dto.getName() + " (" + dto.getLocation() + ") - Rating: " + dto.getRating());
            }
        }
    }

    /**
     * Convenience method that simulates the entire search flow from the sequence diagram.
     * This method combines all steps for easy demonstration.
     *
     * @param location the location filter
     * @param type the type filter
     * @param minRating the minimum rating
     * @param amenities the list of required amenities
     * @return the search results
     */
    public List<RefreshmentPointDto> searchRefreshmentPoints(String location, String type, double minRating, List<String> amenities) {
        activateSearch();
        SearchForm form = showSearchForm();
        form.setLocation(location);
        form.setType(type);
        form.setMinRating(minRating);
        form.setAmenities(amenities);
        return submitForm(form);
    }
}