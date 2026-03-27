package com.agency.reporting;

import com.agency.reporting.application.GenerateReportService;
import com.agency.reporting.application.SearchSiteUseCase;
import com.agency.reporting.auth.AuthenticationService;
import com.agency.reporting.auth.UserSession;
import com.agency.reporting.domain.ReportCalculator;
import com.agency.reporting.infrastructure.cache.Cache;
import com.agency.reporting.infrastructure.external.ExternalSiteFeedbackService;
import com.agency.reporting.infrastructure.persistence.CacheAwareSiteFeedbackRepository;
import com.agency.reporting.infrastructure.persistence.LocationRepository;
import com.agency.reporting.infrastructure.persistence.LocationRepositoryImpl;
import com.agency.reporting.infrastructure.persistence.SiteFeedbackRepository;
import com.agency.reporting.performance.PerformanceMonitor;
import com.agency.reporting.ui.LocationSelectionForm;
import com.agency.reporting.ui.controller.GenerateReportController;
import com.agency.reporting.ui.viewmodel.LocationViewModel;
import com.agency.reporting.ui.viewmodel.ReportViewModel;

/**
 * Main application class to demonstrate the flow.
 */
public class MainApp {
    public static void main(String[] args) {
        // Setup dependencies
        UserSession session = new UserSession();
        AuthenticationService authService = new AuthenticationService(session);
        PerformanceMonitor performanceMonitor = new PerformanceMonitor();
        
        // Login simulation
        session.login("dummy_credentials");
        
        // Infrastructure
        Cache cache = new Cache();
        SearchSiteUseCase searchSiteUseCase = new ExternalSiteFeedbackService();
        SiteFeedbackRepository feedbackRepo = new CacheAwareSiteFeedbackRepository(cache, searchSiteUseCase);
        LocationRepository locationRepo = new LocationRepositoryImpl();
        
        // Domain
        ReportCalculator reportCalculator = new ReportCalculator();
        
        // Application
        GenerateReportService generateReportService = new GenerateReportService(feedbackRepo, reportCalculator);
        GenerateReportController controller = new GenerateReportController(generateReportService, authService);
        
        // UI ViewModels
        LocationViewModel locationViewModel = new LocationViewModel(locationRepo, authService);
        ReportViewModel reportViewModel = new ReportViewModel(controller, authService, performanceMonitor);
        
        // UI Components
        LocationSelectionForm locationForm = new LocationSelectionForm(locationViewModel, reportViewModel);
        
        // Simulate the sequence diagram flow
        System.out.println("=== Starting Statistical Report Flow ===");
        
        // Step 1: Agency Operator activates feature
        System.out.println("\nStep 1: Agency Operator activates statistical report feature");
        
        // Step 2: System uploads list of places and starts timer
        System.out.println("\nStep 2: System initializes ReportViewModel and starts timer");
        reportViewModel.initialize();
        
        System.out.println("\nStep 2 (continued): System loads locations");
        locationViewModel.loadLocations();
        locationForm.displayForm();
        
        // Step 3-4: Selection
        System.out.println("\nStep 3-4: Select a location");
        locationForm.setSelectedLocation("loc1");
        
        // Step 5: Form submission
        System.out.println("\nStep 5: Form submission");
        locationForm.submitForm();
        
        // Step 6-8: Generate and display report (handled in submitForm via reportViewModel.generateReport)
        
        System.out.println("\n=== Flow Completed ===");
    }
}