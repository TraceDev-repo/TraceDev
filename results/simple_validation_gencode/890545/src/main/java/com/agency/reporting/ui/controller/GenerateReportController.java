package com.agency.reporting.ui.controller;

import com.agency.reporting.application.GenerateReportUseCase;
import com.agency.reporting.auth.AuthenticationService;
import com.agency.reporting.auth.UserSession;
import com.agency.reporting.domain.StatisticalReport;

/**
 * Controller that handles report generation requests.
 */
public class GenerateReportController {
    private final GenerateReportUseCase useCase;
    private final AuthenticationService authService;

    public GenerateReportController(GenerateReportUseCase useCase, AuthenticationService authService) {
        this.useCase = useCase;
        this.authService = authService;
    }

    public StatisticalReport handleRequest(String locationId, UserSession session) {
        // Step 1: Validate session as per sequence diagram
        if (!authService.validateSession(session)) {
            throw new SecurityException("Not authenticated");
        }
        // Step 2: Execute use case
        return useCase.execute(locationId);
    }
}