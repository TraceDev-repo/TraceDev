package com.example;

import com.example.infrastructure.DataSource;
import com.example.infrastructure.persistence.TouristRepositoryImpl;
import com.example.application.ports.TouristRepository;
import com.example.application.ModifyTouristAccountUseCase;
import com.example.application.DefaultErroredUseCase;
import com.example.application.ErroredUseCase;
import com.example.application.services.AuthenticationService;
import com.example.interfaces.TouristController;
import com.example.interfaces.TouristControllerImpl;
import com.example.application.dto.TouristDTO;
import com.example.application.Response;

/**
 * Main application class demonstrating the system flow.
 * This simulates the sequence diagram interactions.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Starting Tourist Management System ===\n");
        
        // 1. Setup infrastructure
        DataSource dataSource = new DataSource("jdbc:mysql://localhost:3306/etour", "com.mysql.cj.jdbc.Driver");
        TouristRepository repository = new TouristRepositoryImpl(dataSource);
        ErroredUseCase erroredUseCase = new DefaultErroredUseCase();
        
        // 2. Setup application layer
        ModifyTouristAccountUseCase useCase = new ModifyTouristAccountUseCase(repository, erroredUseCase);
        AuthenticationService authService = new AuthenticationService();
        
        // 3. Setup interface layer
        TouristController controller = new TouristControllerImpl(useCase, authService);
        
        // 4. Simulate the sequence diagram flow
        
        // Entry Condition: Agency Operator HAS logged in
        System.out.println("1. Agency Operator logged in: " + authService.validateLogin());
        
        // Search and Load Tourist Data
        System.out.println("\n2. Searching tourists...");
        var tourists = controller.searchTourists();
        System.out.println("   Found " + tourists.size() + " tourists");
        
        System.out.println("\n3. Getting tourist for edit...");
        TouristDTO touristForEdit = controller.getTouristForEdit("tourist-123");
        if (touristForEdit != null) {
            System.out.println("   Loaded tourist: " + touristForEdit.getName());
        } else {
            System.out.println("   Tourist not found, creating sample data...");
            touristForEdit = new TouristDTO();
            touristForEdit.setId("tourist-123");
            touristForEdit.setName("John Doe");
            touristForEdit.setEmail("john@example.com");
            touristForEdit.setPhoneNumber("123-456-7890");
        }
        
        // Edit and Validate Data
        System.out.println("\n4. Simulating form edit...");
        touristForEdit.setEmail("john.new@example.com");
        touristForEdit.setPhoneNumber("987-654-3210");
        
        System.out.println("\n5. Validating updated data...");
        Response validationResponse = controller.updateTourist("tourist-123", touristForEdit);
        System.out.println("   Validation response: " + validationResponse.getMessage());
        
        if (validationResponse.isSuccess()) {
            System.out.println("\n6. Data is valid, requesting confirmation...");
            System.out.println("   (Simulating user confirmation)");
            
            System.out.println("\n7. Confirming update...");
            Response confirmResponse = controller.confirmUpdate("tourist-123", touristForEdit);
            System.out.println("   Confirm response: " + confirmResponse.getMessage());
        } else {
            System.out.println("\n6. Data invalid, error handling activated.");
        }
        
        // Simulate connection interruption (Alternative Flow)
        System.out.println("\n8. Simulating connection interruption...");
        try {
            dataSource.getConnection();
        } catch (Exception e) {
            System.out.println("   Connection error: " + e.getMessage());
            System.out.println("   Showing critical error message to user");
        }
        
        System.out.println("\n=== System Demonstration Complete ===");
    }
}