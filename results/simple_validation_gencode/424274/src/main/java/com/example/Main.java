package com.example;

import com.example.adapters.controllers.RefreshmentPointController;
import com.example.application.SearchRefreshmentPointsUseCase;
import com.example.infrastructure.*;
import com.example.application.RefreshmentPointDto;

import java.util.List;

/**
 * Main application class that demonstrates the complete flow.
 * This class sets up all dependencies and runs the search use case.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Refreshment Point Search System ===\n");

        // Setup infrastructure layer components
        EServerApiClient apiClient = new EServerApiClient();
        RefreshmentPointCache cache = new RefreshmentPointCache();
        EServerRefreshmentPointRepository repository = new EServerRefreshmentPointRepository(apiClient, cache);

        // Setup application layer
        SearchRefreshmentPointsUseCase useCase = new SearchRefreshmentPointsUseCase(repository);

        // Setup controller
        RefreshmentPointController controller = new RefreshmentPointController(useCase);

        // Simulate the complete search flow from sequence diagram
        System.out.println("=== Starting Search Flow ===\n");

        // Step 1: User activates search
        controller.activateSearch();

        // Step 2: System shows search form
        // This is implicit in the controller.searchRefreshmentPoints method

        // Steps 3-4: User fills and submits form, system processes request
        List<RefreshmentPointDto> results = controller.searchRefreshmentPoints(
                "Downtown",
                "Restaurant",
                4.0,
                List.of("WiFi", "Parking")
        );

        // Display final results
        System.out.println("\n=== Final Results ===");
        if (results != null && !results.isEmpty()) {
            System.out.println("Found " + results.size() + " refreshment point(s):");
            for (RefreshmentPointDto dto : results) {
                System.out.println(" - " + dto.getName() + " at " + dto.getLocation() +
                        " (Rating: " + dto.getRating() + ")");
            }
        } else {
            System.out.println("No results found or search failed.");
        }

        // Demonstrate cache hit scenario
        System.out.println("\n=== Cache Demonstration ===");
        System.out.println("Performing the same search again (should hit cache)...");
        controller.searchRefreshmentPoints(
                "Downtown",
                "Restaurant",
                4.0,
                List.of("WiFi", "Parking")
        );

        System.out.println("\n=== System Completed Successfully ===");
    }
}