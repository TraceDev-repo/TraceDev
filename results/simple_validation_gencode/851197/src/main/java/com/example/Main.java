package com.example;

import com.example.application.ViewCulturalGoodUseCaseController;
import com.example.domain.CulturalGoodType;
import com.example.dto.CulturalGoodDTO;
import com.example.infrastructure.ETOURServerClient;
import com.example.infrastructure.ETOURServerCulturalGoodRepository;
import com.example.presentation.CulturalGoodController;
import com.example.presentation.CulturalGoodDetailView;
import com.example.presentation.CulturalGoodsListView;
import com.example.presentation.SearchCulturalHeritageController;
import com.example.repository.CulturalGoodRepository;
import com.example.service.CulturalGoodQueryService;
import java.util.List;

/**
 * Main class to simulate the flow described in the sequence diagram.
 */
public class Main {
    public static void main(String[] args) {
        // Setup infrastructure
        ETOURServerClient serverClient = new ETOURServerClient("http://etour.example.com/api");
        CulturalGoodRepository repository = new ETOURServerCulturalGoodRepository(serverClient);
        
        // Setup service layer
        CulturalGoodQueryService queryService = new CulturalGoodQueryService(repository);
        
        // Setup application layer
        ViewCulturalGoodUseCaseController useCaseController = new ViewCulturalGoodUseCaseController(queryService);
        
        // Setup presentation layer
        CulturalGoodsListView listView = new CulturalGoodsListView();
        CulturalGoodDetailView detailView = new CulturalGoodDetailView();
        CulturalGoodController controller = new CulturalGoodController(listView, detailView, useCaseController);
        
        // Setup previous use case controller (Search)
        SearchCulturalHeritageController searchController = new SearchCulturalHeritageController(controller);
        
        System.out.println("=== Starting Cultural Heritage System ===");
        
        // Simulate the flow from previous use case (REQ-SEQ-001)
        System.out.println("\n1. Previous use case (Search Cultural Heritage) passes results:");
        List<CulturalGoodDTO> searchResults = searchController.executeSearch("Rome");
        searchController.passResultsToViewCulturalGood(searchResults);
        
        // Simulate actor selecting a cultural good from the list
        System.out.println("\n2. Actor selects a cultural good (ID = 1):");
        listView.selectCulturalGood(1);
        // In real UI, selection triggers controller; here we call controller directly
        controller.handleCulturalGoodSelection(1);
        
        // Simulate another selection that might fail (e.g., ID 99)
        System.out.println("\n3. Actor selects a non-existent cultural good (ID = 99):");
        controller.handleCulturalGoodSelection(99);
        
        System.out.println("\n=== End of Simulation ===");
    }
}