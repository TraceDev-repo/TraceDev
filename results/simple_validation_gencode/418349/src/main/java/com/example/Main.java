package com.example;

import com.example.application.*;
import com.example.infrastructure.*;
import com.example.ui.CulturalObjectSearchController;
import com.example.ui.SearchRequest;
import com.example.domain.Credentials;

import java.util.Date;

/**
 * Main class to demonstrate the system.
 */
public class Main {
    public static void main(String[] args) {
        // Build infrastructure components
        EtourApiClient apiClient = new EtourApiClient();
        ConnectionMonitor monitor = new ConnectionMonitor(apiClient, 5000);
        EtourServerObjectRepository repository = new EtourServerObjectRepository(apiClient, 3000, monitor);

        TimeoutConfiguration timeoutConfig = new TimeoutConfiguration(5000);
        timeoutConfig.setTimeout("search", 3000);
        TimeoutPolicy timeoutPolicy = new TimeoutPolicy(3000, timeoutConfig);

        CredentialsValidator validator = new CredentialsValidator();
        AuthenticationService authService = new AuthenticationService(validator);
        SearchAvailabilityService availabilityService = new SearchAvailabilityService();
        DataValidationService dataValidationService = new DataValidationService(repository);

        // Build query handler (application service)
        SearchCulturalObjectsQueryHandler queryHandler = new SearchCulturalObjectsQueryHandler(
                repository, timeoutPolicy, authService, availabilityService, dataValidationService
        );

        // Build UI controller
        CulturalObjectSearchController controller = new CulturalObjectSearchController(
                queryHandler, authService, availabilityService, dataValidationService
        );

        System.out.println("=== Cultural Object Search System ===\n");

        // Step 1: Authenticate user (simulate login)
        System.out.println("1. Authenticating user...");
        Credentials creds = new Credentials("demo", "demo123");
        authService.authenticateUser(creds);
        System.out.println("   User authenticated: " + authService.isUserAuthenticated());

        // Step 2: Activate search (performs entry condition validation)
        System.out.println("\n2. Activating search...");
        controller.activateSearch(); // Should show search form

        // Step 3: Perform a search
        System.out.println("\n3. Performing search...");
        DateRange dateRange = new DateRange(new Date(System.currentTimeMillis() - 86400000L), new Date());
        SearchRequest request = new SearchRequest("ancient", "Artifact", "Rome", dateRange);
        SearchResultDTO result = controller.search(request);

        if (result != null) {
            System.out.println("   Search successful!");
        } else {
            System.out.println("   Search failed.");
        }

        System.out.println("\n=== End of demonstration ===");
    }
}