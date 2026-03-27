package com.etour.validation;

import com.etour.dto.RefreshmentPointDTO;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of IDTOValidator.
 */
public class DTOValidator implements IDTOValidator {
    @Override
    public ValidationResult validate(RefreshmentPointDTO dto) {
        List<String> errors = new ArrayList<>();
        if (dto.id == null || dto.id.trim().isEmpty()) {
            errors.add("ID cannot be empty");
        }
        if (dto.name == null || dto.name.trim().isEmpty()) {
            errors.add("Name cannot be empty");
        }
        if (dto.address == null || dto.address.trim().isEmpty()) {
            errors.add("Address cannot be empty");
        }
        boolean isValid = errors.isEmpty();
        return new ValidationResult(isValid, errors);
    }
}