package com.etour.view;

import com.etour.dto.RefreshmentPointDTO;
import com.etour.validation.IDTOValidator;

/**
 * Represents the form for editing a refreshment point.
 * Includes locking to prevent multiple submissions.
 */
public class ChangeForm {
    private RefreshmentPointDTO dto;
    private IDTOValidator validator;
    private boolean controlsLocked = false;

    public ChangeForm(IDTOValidator validator) {
        this.validator = validator;
    }

    /**
     * Loads data into the form.
     * @param dto The DTO containing current point data.
     */
    public void loadData(RefreshmentPointDTO dto) {
        this.dto = dto;
        System.out.println("Form loaded with data for point: " + dto.name);
    }

    /**
     * Simulates retrieving user input from the form.
     * @return A DTO with the updated data.
     */
    public RefreshmentPointDTO getUserInput() {
        // In a real form, this would read values from UI fields.
        System.out.println("Form capturing user input.");
        return dto;
    }

    /**
     * Submits the form. Returns the updated DTO.
     * Note: Locking is managed by the controller after confirmation.
     * @return The updated DTO.
     */
    public RefreshmentPointDTO submitForm() {
        System.out.println("Form submitted.");
        // Return the DTO (controller will handle validation and locking)
        return getUserInput();
    }

    /**
     * Cancels the form.
     */
    public void cancelForm() {
        System.out.println("Form cancelled.");
    }

    /**
     * Locks the form controls to prevent multiple submissions.
     */
    public void lockControls() {
        controlsLocked = true;
        System.out.println("Form controls locked.");
    }

    /**
     * Unlocks the form controls.
     */
    public void unlockControls() {
        controlsLocked = false;
        System.out.println("Form controls unlocked.");
    }

    /**
     * Checks if controls are locked.
     * @return true if locked, false otherwise.
     */
    public boolean isControlsLocked() {
        return controlsLocked;
    }
}