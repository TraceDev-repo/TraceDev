package com.example.controller;

import com.example.repository.IConventionRepository;
import com.example.dto.ConventionDTO;
import com.example.domain.Agency;
import com.example.dao.AgencyDAO;
import com.example.data.ConventionData;
import com.example.view.HistoryDisplayView;
import com.example.dao.FormDAO;
import com.example.domain.Form;
import com.example.exception.ConnectionException;
import java.util.List;

// Controller class coordinating the view convention history use case
public class UseCaseController {
    private IConventionRepository conventionRepository;
    private AgencyDAO agencyDAO;
    private FormDAO formDAO;
    private HistoryDisplayView view;

    public UseCaseController(IConventionRepository conventionRepository, AgencyDAO agencyDAO, FormDAO formDAO, HistoryDisplayView view) {
        this.conventionRepository = conventionRepository;
        this.agencyDAO = agencyDAO;
        this.formDAO = formDAO;
        this.view = view;
    }

    // Main entry point for viewing convention history
    public void handleViewConventionHistory() {
        // This is called internally as shown in the sequence diagram
        // Actual implementation would get agencyId from somewhere (e.g., session or parameters)
        int agencyId = 1; // Default agency ID for demonstration
        boolean preconditionsMet = validateAgencyPreconditions(agencyId);

        if (!preconditionsMet) {
            // According to sequence diagram, error should be displayed immediately.
            // Already shown in validateAgencyPreconditions.
            return; // Error already shown by validateAgencyPreconditions
        }

        try {
            List<ConventionDTO> conventions = conventionRepository.getHistoryByAgencyId(agencyId);
            view.displayHistory(conventions);
        } catch (ConnectionException e) {
            // Handle connection exception distinctly
            view.showError("Connection error: " + e.getMessage());
        } catch (RuntimeException e) {
            // Handle other exceptions
            view.showError(e.getMessage());
        }
    }

    // Validates agency preconditions before allowing data upload
    public boolean validateAgencyPreconditions(int agencyId) {
        Agency agency = agencyDAO.getById(agencyId);

        if (agency == null) {
            view.showError("Agency not found.");
            return false;
        }

        Form form = formDAO.getFormByAgencyId(agencyId);

        // Check both conditions: must be designated point of rest AND have a form
        if (!agency.isDesignatedPointOfRest() || form == null) {
            view.showError("Preconditions not met. Agency must be a designated point of rest and have a form.");
            return false;
        }

        return true;
    }

    // New method to trigger precondition validation when user accesses the feature
    // This addresses the sequence diagram requirement: precondition validation before upload
    public void userAccessesFeature() {
        // Get agencyId from session or context; for demonstration use default.
        int agencyId = 1;
        boolean preconditionsMet = validateAgencyPreconditions(agencyId);
        if (!preconditionsMet) {
            // Error already shown by validateAgencyPreconditions, do not proceed.
            return;
        }
        // If preconditions are met, the system can proceed to prompt for data upload.
        // The view will call promptForConventionData later.
        // This method could be called from the view when the user clicks the upload button.
    }

    // Handles upload of convention data after successful precondition validation
    public void uploadConventionData(ConventionData selectedData) {
        // Validate preconditions using the agency ID from the uploaded data
        int agencyId = selectedData.getAgencyId();
        boolean preconditionsMet = validateAgencyPreconditions(agencyId);

        if (!preconditionsMet) {
            return; // Error already shown by validateAgencyPreconditions
        }

        // Process the uploaded data (in real implementation, this would save the data)
        System.out.println("Processing uploaded convention data for agency: " + agencyId);

        // After processing, show the history
        handleViewConventionHistory();
    }
}