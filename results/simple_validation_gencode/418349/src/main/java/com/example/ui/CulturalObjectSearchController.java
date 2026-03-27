package com.example.ui;

import com.example.application.*;
import com.example.infrastructure.*;
import com.example.domain.Credentials;

/**
 * UI Layer (Adapters). Includes comprehensive entry condition validation.
 */
public class CulturalObjectSearchController {
    private final QueryHandler<SearchCulturalObjectsQuery, SearchResultDTO> queryHandler;
    private final AuthenticationService authService;
    private final SearchAvailabilityService searchAvailabilityService;
    private final DataValidationService dataValidationService;

    public CulturalObjectSearchController(QueryHandler<SearchCulturalObjectsQuery, SearchResultDTO> queryHandler,
                                         AuthenticationService authService,
                                         SearchAvailabilityService searchAvailabilityService,
                                         DataValidationService dataValidationService) {
        this.queryHandler = queryHandler;
        this.authService = authService;
        this.searchAvailabilityService = searchAvailabilityService;
        this.dataValidationService = dataValidationService;
    }

    /**
     * Step 1 in sequence diagram: activateSearch.
     */
    public void activateSearch() {
        boolean allConditionsMet = checkAllEntryConditions();
        if (allConditionsMet) {
            showSearchForm();
        } else {
            determineFailedCondition();
        }
    }

    /**
     * Combined entry condition validation (step 1.1).
     * @return true if all conditions are satisfied.
     */
    public boolean checkAllEntryConditions() {
        boolean authenticated = authService.isUserAuthenticated();
        boolean searchAvailable = searchAvailabilityService.isSearchAvailable();
        boolean hasData = dataValidationService.hasData();
        return authenticated && searchAvailable && hasData;
    }

    /**
     * Determines which condition failed and displays appropriate message.
     */
    private void determineFailedCondition() {
        if (!authService.isUserAuthenticated()) {
            displayAccessDenied("Authentication required");
        } else if (!searchAvailabilityService.isSearchAvailable()) {
            displayServiceUnavailable("Search functionality offline");
        } else if (!dataValidationService.hasData()) {
            displayInfoMessage("No cultural data available");
        }
    }

    public void showSearchForm() {
        System.out.println("Showing search form...");
    }

    /**
     * Step 3 in sequence diagram: fillAndSubmitSearchForm.
     */
    public SearchResultDTO search(SearchRequest request) {
        if (!validateSearchRequest(request)) {
            displayValidationErrors();
            return null;
        }

        // Map request to query (as per class diagram relationship <<mapTo>>)
        SearchCulturalObjectsQuery query = mapToQuery(request);

        SearchResultDTO result = queryHandler.handle(query);
        
        // Check for specific error DTO types
        if (result instanceof ConnectionErrorResultDTO) {
            displayErrorMessage("Connection error: " + ((ConnectionErrorResultDTO) result).getErrorMessage());
            return null;
        } else if (result instanceof TimeoutResultDTO) {
            displayErrorMessage("Timeout: " + ((TimeoutResultDTO) result).getTimeoutMessage());
            return null;
        }
        
        displayResults(result);
        return result;
    }

    /**
     * Validates the search request (including date range).
     */
    public boolean validateSearchRequest(SearchRequest request) {
        if (request == null || !request.isValid()) {
            return false;
        }
        // Additional date range validation (as per sequence diagram).
        return request.validateDateRange();
    }

    /**
     * Maps a SearchRequest to a SearchCulturalObjectsQuery.
     */
    private SearchCulturalObjectsQuery mapToQuery(SearchRequest request) {
        return new SearchCulturalObjectsQuery(
                request.getKeyword(),
                request.getObjectType(),
                request.getLocation(),
                request.getDateRange()
        );
    }

    // UI display methods (simulated)
    private void displayAccessDenied(String message) {
        System.out.println("Access Denied: " + message);
    }

    private void displayServiceUnavailable(String message) {
        System.out.println("Service Unavailable: " + message);
    }

    private void displayInfoMessage(String message) {
        System.out.println("Info: " + message);
    }

    private void displayValidationErrors() {
        System.out.println("Validation errors in search request.");
    }

    private void displayErrorMessage(String message) {
        System.out.println("Error: " + message);
    }

    private void displayResults(SearchResultDTO resultDTO) {
        System.out.println("=== Search Results ===");
        System.out.println("Total: " + resultDTO.getTotalCount() +
                " (took " + resultDTO.getSearchTimeMs() + " ms)");
        for (CulturalObjectDTO dto : resultDTO.getResults()) {
            System.out.println(" - " + dto.getTitle() + " [" + dto.getType() + "]");
        }
    }

    /**
     * Logs validation errors (as per sequence diagram).
     */
    private void logValidationErrors() {
        System.out.println("Validation errors logged.");
    }
}