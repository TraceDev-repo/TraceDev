package com.agency;

import com.agency.adapter.EtourServerAdapter;
import com.agency.circuitbreaker.CircuitBreaker;
import com.agency.controller.BannerManagementController;
import com.agency.dto.SearchCriteria;
import com.agency.entity.AgencyOperator;
import com.agency.entity.Credentials;
import com.agency.repository.BannerRepository;
import com.agency.repository.JdbcBannerRepository;
import com.agency.repository.JdbcRefreshmentPointRepository;
import com.agency.repository.RefreshmentPointRepository;
import com.agency.service.AuthenticationService;
import com.agency.service.AuthorizationService;
import com.agency.service.BannerService;

/**
 * Main application class to demonstrate the banner deletion flow.
 */
public class MainApplication {
    public static void main(String[] args) {
        System.out.println("=== Banner Management System Simulation ===");

        // Initialize dependencies
        RefreshmentPointRepository refreshmentPointRepo = new JdbcRefreshmentPointRepository();
        BannerRepository bannerRepo = new JdbcBannerRepository();
        AuthorizationService authorizationService = new AuthorizationService();
        AuthenticationService authenticationService = new AuthenticationService();
        EtourServerAdapter etourAdapter = new EtourServerAdapter();
        CircuitBreaker circuitBreaker = new CircuitBreaker();

        // Authenticate operator (precondition)
        Credentials credentials = new Credentials("admin", "password");
        authenticationService.authenticate(credentials);
        System.out.println("Operator authenticated.");

        // Create banner service
        BannerService bannerService = new BannerService(refreshmentPointRepo, bannerRepo,
                authorizationService, authenticationService, etourAdapter, circuitBreaker);

        // Create controller
        BannerManagementController controller = new BannerManagementController(bannerService);

        // Set controller state based on authentication
        AgencyOperator currentOperator = authenticationService.getCurrentOperator();
        controller.setCurrentOperator(currentOperator);
        controller.setAuthenticated(currentOperator != null && currentOperator.isAuthenticated());
        controller.setAuthorized(currentOperator != null && currentOperator.isAuthorized());

        // Step 1: Get refreshment points
        SearchCriteria criteria = new SearchCriteria("Main", null, null);
        var points = controller.getRefreshmentPoints(criteria);
        System.out.println("Found " + points.size() + " refreshment points.");

        // Step 2: Select a refreshment point
        String selectedPointId = "rp1";
        var selectedPoint = controller.selectRefreshmentPoint(selectedPointId);
        System.out.println("Selected: " + selectedPoint.getName());

        // Step 3: Get banners for the selected point
        var banners = controller.getBannersForRefreshmentPoint(selectedPointId);
        System.out.println("Found " + banners.size() + " banners.");

        // Step 4: Access removal function (now without parameter)
        if (controller.accessBannerRemoval()) {
            System.out.println("Banner removal function accessed.");
        }

        // Step 5 & 6: Simulate deletion confirmation with explicit confirmation method
        String bannerToDelete = "b1";
        if (controller.confirmDeletion(bannerToDelete)) {
            System.out.println("User confirms deletion of banner " + bannerToDelete);
            boolean deleted = controller.deleteBanner(bannerToDelete);
            if (deleted) {
                System.out.println("Banner deletion completed successfully.");
            } else {
                System.out.println("Banner deletion failed.");
            }
        } else {
            System.out.println("Deletion cancelled by user.");
        }

        System.out.println("=== Simulation Complete ===");
    }
}