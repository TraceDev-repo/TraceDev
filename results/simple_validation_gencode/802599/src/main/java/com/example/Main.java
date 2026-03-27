package com.example;

import com.example.application.*;
import com.example.ui.*;
import com.example.infrastructure.ETOURServerDataSource;

/**
 * Main class to demonstrate the flow.
 */
public class Main {
    public static void main(String[] args) {
        // Setup infrastructure
        ETOURServerDataSource dataSource = new ETOURServerDataSource();
        RefreshmentPointRepository repository = new RefreshmentPointRepository(dataSource);
        RefreshmentPointCache cache = new RefreshmentPointCache();
        ViewRefreshmentPointDetailsService service = new ViewRefreshmentPointDetailsService(repository, cache);
        ViewRefreshmentPointDetailsController controller = new ViewRefreshmentPointDetailsController(service);
        RefreshmentPointUICoordinator coordinator = new RefreshmentPointUICoordinator(controller);
        SearchResultsUI searchUI = new SearchResultsUI();
        searchUI.setCoordinator(coordinator);

        // Simulate the flow from search to details
        System.out.println("=== Simulating Search Results Display ===");
        // Mock some results
        RefreshmentPointDetailsDTO dto1 = new RefreshmentPointDetailsDTO(1L, "Cafe Alpha", "Main Street", "A cozy cafe", "123-456");
        RefreshmentPointDetailsDTO dto2 = new RefreshmentPointDetailsDTO(2L, "Restaurant Beta", "Central Plaza", "Fine dining", "789-012");
        searchUI.displayResults(java.util.List.of(dto1, dto2));

        System.out.println("\n=== Simulating User Selection ===");
        searchUI.onItemSelected(1L);

        System.out.println("\n=== Simulating Another Selection (with cache) ===");
        searchUI.onItemSelected(1L); // Should now come from cache

        System.out.println("\n=== Simulating Error Flow (Connection Interrupted) ===");
        // To simulate error, we could inject a faulty data source.
        // For simplicity, we just call the controller with a non-existent ID that causes exception.
        try {
            controller.viewDetails(999L);
        } catch (Exception e) {
            System.out.println("Error handled: " + e.getMessage());
        }
    }
}