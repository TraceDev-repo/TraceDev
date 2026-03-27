package com.etour.validation;

import com.etour.dto.RefreshmentPointDTO;

/**
 * Interface for validating RefreshmentPointDTO objects.
 */
public interface IDTOValidator {
    /**
     * Validates a DTO.
     * @param dto The DTO to validate.
     * @return A ValidationResult containing validation status and errors.
     */
    ValidationResult validate(RefreshmentPointDTO dto);
}