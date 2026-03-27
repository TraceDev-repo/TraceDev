package com.etour.tourist;

import com.etour.tourist.ui.TouristCardDisplay;
import com.etour.tourist.controller.ViewTouristDetailsController;
import com.etour.tourist.usecase.ViewTouristDetailsUseCase;
import com.etour.tourist.repository.TouristRepository;
import com.etour.tourist.dto.TouristDTO;
import com.etour.tourist.usecase.ISearchTouristUseCase;
import java.util.List;
import java.util.Arrays;

/**
 * Main class to simulate the sequence diagram flow.
 * This class acts as the entry point for running the application.
 */
public class Main {
    public static void main(String[] args) {
        // Setup the infrastructure
        TouristRepository repository = new TouristRepository();
        ViewTouristDetailsUseCase useCase = new ViewTouristDetailsUseCase(repository);
        ViewTouristDetailsController controller = new ViewTouristDetailsController(useCase);

        // Create a dummy search use case for demonstration (as required by the diagram)
        ISearchTouristUseCase searchUseCase = criteria -> {
            // Simulate returning a list of tourists (for simplicity, return a static list)
            TouristDTO tourist1 = new TouristDTO(new com.etour.tourist.domain.Tourist("T001", "John Doe", "john@example.com", null));
            TouristDTO tourist2 = new TouristDTO(new com.etour.tourist.domain.Tourist("T002", "Jane Smith", "jane@example.com", null));
            return Arrays.asList(tourist1, tourist2);
        };

        // Create the UI
        TouristCardDisplay display = new TouristCardDisplay(controller, searchUseCase);

        // Simulate the flow from the sequence diagram:
        // Step 1: Obtain tourist list from SearchTourist use case (simulated)
        List<TouristDTO> touristList = searchUseCase.searchTourist("");
        display.setTouristList(touristList);

        // Step 2: Agency Operator selects a tourist from the list
        display.selectTouristFromList(touristList.get(0)); // Select John Doe (T001)
        // Step 2 separate call as per sequence diagram
        display.storeSelectedTouristId(touristList.get(0).getId());

        // Step 3: Activate the display
        display.activateDisplay();

        // Additional test: simulate a cache hit
        System.out.println("\n--- Second request (should be cached) ---");
        display.activateDisplay();

        // Additional test: simulate an error (tourist not found)
        System.out.println("\n--- Testing error scenario ---");
        display.setSelectedTouristId("nonExistentId");
        display.activateDisplay();

        // Test connection error scenario
        System.out.println("\n--- Testing connection error scenario ---");
        display.setSelectedTouristId("errorId");
        display.activateDisplay();
    }
}