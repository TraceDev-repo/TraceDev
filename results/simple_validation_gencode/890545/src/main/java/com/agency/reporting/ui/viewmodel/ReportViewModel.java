package com.agency.reporting.ui.viewmodel;

import com.agency.reporting.auth.AuthenticationService;
import com.agency.reporting.domain.StatisticalReport;
import com.agency.reporting.performance.PerformanceMonitor;
import com.agency.reporting.ui.controller.GenerateReportController;
import com.agency.reporting.auth.UserSession;

/**
 * ViewModel for report generation and display.
 */
public class ReportViewModel {
    private StatisticalReport report;
    private boolean isLoading;
    private final GenerateReportController controller;
    private final AuthenticationService authService;
    private final PerformanceMonitor performanceMonitor;

    public ReportViewModel(GenerateReportController controller, AuthenticationService authService, PerformanceMonitor performanceMonitor) {
        this.controller = controller;
        this.authService = authService;
        this.performanceMonitor = performanceMonitor;
    }

    public StatisticalReport getReport() {
        return report;
    }

    public boolean isLoading() {
        return isLoading;
    }

    public void generateReport(String locationId) {
        UserSession session = new UserSession(); // In real app, this would come from context
        session.login(null); // Simulate login for demo

        if (!authService.validateSession(session)) {
            handleError("Not authenticated");
            return;
        }

        isLoading = true;

        try {
            // Use controller to handle request
            this.report = controller.handleRequest(locationId, session);
            isLoading = false;

            // Check timeout
            if (checkTimeout()) {
                handleError("Report generation timed out");
                performanceMonitor.stopTimer("display_timeout");
            } else {
                System.out.println("Report generated successfully: " + report.generateReportData());
                performanceMonitor.stopTimer("display_timeout");
            }
        } catch (Exception e) {
            handleError(e.getMessage());
        }
    }

    public void startDisplayTimer() {
        performanceMonitor.startTimer("display_timeout", 3000); // 3 seconds
    }

    public boolean checkTimeout() {
        return performanceMonitor.isTimeout("display_timeout");
    }

    public void handleError(String error) {
        System.err.println("Error: " + error);
        // In a real UI, this would update error state for binding
    }

    public void initialize() {
        // Initialize view model state
        this.report = null;
        this.isLoading = false;
        // Start timer at the beginning as per sequence diagram
        startDisplayTimer();
    }
}