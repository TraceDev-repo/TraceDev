package com.etour.controller;

import com.etour.dto.RefreshmentPointDTO;
import com.etour.model.RefreshmentPoint;
import com.etour.service.IRestPointService;
import com.etour.validation.IDTOValidator;
import com.etour.validation.ValidationResult;
import com.etour.view.ChangeForm;

/**
 * Coordinates the edit refreshment point use case.
 * Handles point selection, validation, confirmation, and update.
 */
public class EditRefreshmentPointController {
    private IRestPointService restPointService;
    private IDTOValidator dtoValidator;
    private ChangeForm changeForm;

    public EditRefreshmentPointController(IRestPointService restPointService, IDTOValidator dtoValidator, ChangeForm changeForm) {
        this.restPointService = restPointService;
        this.dtoValidator = dtoValidator;
        this.changeForm = changeForm;
    }

    /**
     * Handles selection of a refreshment point by ID.
     * Fetches the point and converts it to a DTO for editing.
     * @param pointId The ID of the selected point.
     * @return The DTO of the selected point.
     */
    public RefreshmentPointDTO handleRefreshmentPointSelection(String pointId) {
        System.out.println("Controller handling point selection for ID: " + pointId);
        RefreshmentPoint point = restPointService.getRefreshmentPointById(pointId);
        if (point == null) {
            throw new IllegalArgumentException("Refreshment point not found for ID: " + pointId);
        }
        // Convert RefreshmentPoint to RefreshmentPointDTO
        RefreshmentPointDTO dto = new RefreshmentPointDTO();
        dto.id = point.getId();
        dto.name = point.getName();
        dto.address = point.getAddress();
        dto.amenities = point.getAmenities();
        return dto;
    }

    /**
     * Displays the change form with the current data.
     * @param data The DTO containing the current point data.
     */
    public void displayChangeForm(RefreshmentPointDTO data) {
        System.out.println("Controller displaying change form for point: " + data.name);
        changeForm.loadData(data);
    }

    /**
     * Processes the form submission: validates, requests confirmation, and updates.
     * @param updatedData The DTO with updated data from the form.
     * @return true if the update was successful, false otherwise.
     */
    public boolean processFormSubmission(RefreshmentPointDTO updatedData) {
        System.out.println("Controller processing form submission for point ID: " + updatedData.id);

        // Check lock state before processing (Quality Requirement)
        if (changeForm != null && changeForm.isControlsLocked()) {
            System.out.println("Form controls are locked. Submission blocked to prevent multiple submissions.");
            return false;
        }

        // Validate the DTO (Flow of Events 8)
        ValidationResult validationResult = dtoValidator.validate(updatedData);
        if (!validationResult.getIsValid()) {
            System.out.println("Validation failed: " + validationResult.getErrors());
            return false;
        }

        // Request confirmation (Flow of Events 9)
        boolean confirmed = requestConfirmation(updatedData);
        if (!confirmed) {
            System.out.println("Update cancelled by operator.");
            return false;
        }

        // Lock controls to prevent multiple submissions (Quality Requirement)
        if (changeForm != null) {
            changeForm.lockControls();
        }

        // Convert DTO to entity and update
        RefreshmentPoint point = convertDtoToEntity(updatedData);
        boolean success = restPointService.updateRefreshmentPoint(point);

        // Unlock controls after operation
        if (changeForm != null) {
            changeForm.unlockControls();
        }

        return success;
    }

    /**
     * Asks the operator for confirmation before updating.
     * @param data The DTO with the changes.
     * @return true if confirmed, false if denied.
     */
    public boolean requestConfirmation(RefreshmentPointDTO data) {
        // In a real system, this would show a confirmation dialog.
        // For simulation, we assume the operator always confirms.
        System.out.println("Controller requesting confirmation for changes to point: " + data.name);
        System.out.println("  New address: " + data.address);
        System.out.println("  New amenities: " + data.amenities);
        // Placeholder for user interaction - currently simulated always true
        return true; // Simulating operator confirmation
    }

    /**
     * Confirms the transaction (called by the operator).
     * @return true if confirmation succeeded.
     */
    public boolean confirmTransaction() {
        // This method would be triggered by a UI confirmation.
        // In this implementation, confirmation is handled in processFormSubmission.
        System.out.println("Transaction confirmed.");
        return true;
    }

    /**
     * Cancels the current operation.
     */
    public void cancelOperation() {
        System.out.println("Operation cancelled by operator.");
        if (changeForm != null) {
            changeForm.cancelForm();
        }
    }

    /**
     * Converts a RefreshmentPointDTO to a RefreshmentPoint entity.
     */
    private RefreshmentPoint convertDtoToEntity(RefreshmentPointDTO dto) {
        RefreshmentPoint point = new RefreshmentPoint();
        point.setId(dto.id);
        point.setName(dto.name);
        point.setAddress(dto.address);
        point.setAmenities(dto.amenities);
        return point;
    }
}