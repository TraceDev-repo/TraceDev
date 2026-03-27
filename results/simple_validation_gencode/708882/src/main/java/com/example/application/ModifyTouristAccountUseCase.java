package com.example.application;

import com.example.application.ports.TouristRepository;
import com.example.application.dto.TouristDTO;
import com.example.application.validation.ValidationResult;
import com.example.domain.Tourist;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Use case for modifying tourist account information.
 * Contains business logic for tourist data modification.
 */
public class ModifyTouristAccountUseCase {
    private TouristRepository touristRepository;
    private ErroredUseCase erroredUseCase;

    /**
     * Constructor.
     * @param repo the tourist repository
     * @param erroredUseCase the error handling use case
     */
    public ModifyTouristAccountUseCase(TouristRepository repo, ErroredUseCase erroredUseCase) {
        this.touristRepository = repo;
        this.erroredUseCase = erroredUseCase;
    }

    /**
     * Searches for all tourists.
     * @return list of tourist DTOs
     */
    public List<TouristDTO> searchTourists() {
        List<Tourist> tourists = touristRepository.findAll();
        return tourists.stream()
                .map(TouristDTO::new)
                .collect(Collectors.toList());
    }

    /**
     * Loads tourist data for editing.
     * @param touristId the ID of the tourist to load
     * @return the tourist DTO, or null if not found
     */
    public TouristDTO loadTouristData(String touristId) {
        return touristRepository.findById(touristId)
                .map(TouristDTO::new)
                .orElse(null);
    }

    /**
     * Validates tourist data.
     * @param dto the tourist DTO to validate
     * @return validation result
     */
    public ValidationResult validateTouristData(TouristDTO dto) {
        ValidationResult result = new ValidationResult();
        
        if (dto.getName() == null || dto.getName().trim().isEmpty()) {
            result.addErrorMessage("Name is required");
        }
        
        if (dto.getEmail() == null || dto.getEmail().trim().isEmpty()) {
            result.addErrorMessage("Email is required");
        } else if (!dto.getEmail().contains("@")) {
            result.addErrorMessage("Email must be valid");
        }
        
        if (dto.getPhoneNumber() == null || dto.getPhoneNumber().trim().isEmpty()) {
            result.addErrorMessage("Phone number is required");
        }
        
        return result;
    }

    /**
     * Handles validation failure by activating the error use case.
     * @param errorMessages list of error messages
     */
    public void handleValidationFailure(List<String> errorMessages) {
        erroredUseCase.activate(errorMessages);
    }

    /**
     * Confirms and updates tourist data (without explicit user confirmation).
     * @param touristId the tourist ID
     * @param updatedData the updated tourist data
     * @return the updated tourist entity
     */
    public Tourist confirmAndUpdate(String touristId, TouristDTO updatedData) {
        // Find existing tourist
        Tourist tourist = touristRepository.findById(touristId)
                .orElseThrow(() -> new IllegalArgumentException("Tourist not found"));
        
        // Update fields
        tourist.setName(updatedData.getName());
        tourist.setEmail(updatedData.getEmail());
        tourist.setPhoneNumber(updatedData.getPhoneNumber());
        
        // Save and return
        return touristRepository.save(tourist);
    }

    /**
     * Explicit confirmation and update handling.
     * @param touristId the tourist ID
     * @param updatedData the updated tourist data
     * @return the updated tourist entity
     */
    public Tourist confirmUpdate(String touristId, TouristDTO updatedData) {
        return confirmAndUpdate(touristId, updatedData);
    }
}