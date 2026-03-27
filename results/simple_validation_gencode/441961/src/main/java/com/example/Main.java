package com.example;

import com.example.controller.SearchTouristController;
import com.example.database.ETOURDataSource;
import com.example.repository.TouristAccountRepository;
import com.example.service.AuthenticationService;
import com.example.service.IAuthenticationService;
import com.example.service.SessionManager;
import com.example.ui.SearchFormUI;
import com.example.validation.SearchValidator;

/**
 * Main class to run the application.
 * Sets up the dependency graph and starts the UI flow.
 */
public class Main {
    public static void main(String[] args) {
        // 1. Create dependencies
        ETOURDataSource dataSource = new ETOURDataSource();
        TouristAccountRepository repository = new TouristAccountRepository(dataSource);
        IAuthenticationService authService = new AuthenticationService();
        SessionManager sessionManager = new SessionManager();
        SearchValidator validator = new SearchValidator();

        // 2. Create controller with injected dependencies
        SearchTouristController controller = new SearchTouristController(
                repository, authService, sessionManager, validator);

        // 3. Create UI with controller and session manager
        SearchFormUI ui = new SearchFormUI(controller, sessionManager);

        // 4. Start the search flow (simulates Agency Operator interaction)
        ui.startSearchFlow();
    }
}