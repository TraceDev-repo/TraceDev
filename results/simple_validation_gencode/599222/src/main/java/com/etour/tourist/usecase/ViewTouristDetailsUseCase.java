package com.etour.tourist.usecase;

import com.etour.tourist.domain.Tourist;
import com.etour.tourist.dto.TouristDTO;
import com.etour.tourist.repository.ITouristRepository;
import com.etour.tourist.domain.TouristNotFoundException;

/**
 * The use case implementation for viewing tourist details.
 */
public class ViewTouristDetailsUseCase implements IViewTouristDetailsUseCase {
    private ITouristRepository touristRepository;

    public ViewTouristDetailsUseCase(ITouristRepository touristRepository) {
        this.touristRepository = touristRepository;
    }

    @Override
    public TouristDTO execute(String selectedTouristId) {
        try {
            // Step 5a: Call repository to find tourist
            Tourist tourist = touristRepository.findById(selectedTouristId);
            // Step 9: Convert to DTO
            return tourist.getTouristDTO();
        } catch (TouristNotFoundException e) {
            // Re-throw the exception to be handled by the controller
            throw e;
        }
    }
}