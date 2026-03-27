package com.agency;

import java.util.List;

/**
 * Main class to demonstrate the system.
 */
public class Main {
    public static void main(String[] args) {
        // Setup dependencies.
        SessionManager sessionManager = new SessionManager();
        IAuthenticationService authService = new AuthenticationService(sessionManager);
        IConfirmationService confirmationService = new ConsoleConfirmationService();
        IConnectionManager connectionManager = new ETOURConnectionManager("http://etour.server", 5000);
        DataSource dataSource = new DataSource();
        ITouristRepository touristRepository = new TouristRepository(dataSource, connectionManager);

        // Create controllers.
        SearchTouristController searchController = new SearchTouristController(touristRepository);
        DeleteTouristAccountController deleteController = new DeleteTouristAccountController(
                touristRepository, confirmationService, authService, connectionManager, searchController);

        // Create an agency operator with authentication service.
        AgencyOperator operator = new AgencyOperator("OP001", "Alice Operator", authService);

        // Step 1: Search tourists using controller's default criteria.
        System.out.println("=== Step 1: Search Tourists ===");
        SearchCriteria criteria = new SearchCriteria();
        criteria.setActiveOnly(true);
        searchController.setSearchCriteria(criteria);
        List<Tourist> tourists = searchController.searchTourists();
        System.out.println("Found " + tourists.size() + " tourists:");
        for (Tourist t : tourists) {
            System.out.println("  " + t);
        }

        // Step 2: Select a tourist.
        System.out.println("\n=== Step 2: Select Tourist ===");
        String selectedTouristId = "T001";
        Tourist selected = searchController.selectTourist(selectedTouristId);
        System.out.println("Selected tourist: " + selected);

        // Step 3: Delete tourist account.
        System.out.println("\n=== Step 3: Delete Tourist Account ===");
        deleteController.execute(operator, selectedTouristId);

        System.out.println("\nProcess completed.");
    }
}