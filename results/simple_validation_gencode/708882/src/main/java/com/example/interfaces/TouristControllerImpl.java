
package com.example.interfaces;

import com.example.application.ModifyTouristAccountUseCase;
import com.example.application.services.AuthenticationService;
import com.example.application.dto.TouristDTO;
import com.example.application.validation.ValidationResult;
import com.example.application.Response;
import com.example.domain.Tourist;
import java.util.List;

/**
 * Implementation of TouristController.
 * Orchestrates tourist management operations and handles authentication.
 */
public class TouristControllerImpl implements TouristController {
    private ModifyTouristAccountUseCase useCase;
    private AuthenticationService authService;

    /**
     * Constructor.
     * @param useCase the modify tourist account use case
     * @param authService the authentication service
     */
    public TouristControllerImpl(ModifyTouristAccountUseCase useCase, AuthenticationService authService) {
        this.useCase = useCase;
        this.authService = authService;
    }

    /**
     * Validates that the current user is authenticated.
     * @throws IllegalStateException if not authenticated
     */
    private void validateLogin() {
        if (!authService.validateLogin()) {
            throw new IllegalStateException("User not authenticated");
        }
    }

    @Override
    public List<TouristDTO> searchTourists() {
        validateLogin();
        return useCase.searchTourists();
    }

    @Override
    public TouristDTO getTouristForEdit(String touristId) {
        validateLogin();
        return useCase.loadTouristData(touristId);
    }

    @Override
    public Response updateTourist(String touristId, TouristDTO dto) {
        validateLogin();
        
        // Validate the data
        ValidationResult validationResult = useCase.validateTouristData(dto);
        
        if (validationResult.getIsValid()) {
            // Data is valid, require confirmation
            return new Response(true, "Please confirm changes", dto);
        } else {
            // Data is invalid, handle validation failure
            useCase.handleValidationFailure(validationResult.getErrorMessages());
            return new Response(false, String.join(", ", validationResult.getErrorMessages()), null);
        }
    }

    @Override
    public Response confirmUpdate(String touristId, TouristDTO dto) {
        validateLogin();
        
        try {
            Tourist updatedTourist = useCase.confirmUpdate(touristId, dto);
            return new Response(true, "Update successful", updatedTourist);
        } catch (Exception e) {
            return new Response(false, "Update failed: " + e.getMessage(), null);
        }
    }
}
