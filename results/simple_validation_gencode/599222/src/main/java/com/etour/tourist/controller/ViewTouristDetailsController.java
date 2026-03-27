package com.etour.tourist.controller;

import com.etour.tourist.usecase.IViewTouristDetailsUseCase;
import com.etour.tourist.dto.TouristDTO;
import com.etour.tourist.domain.TouristNotFoundException;

/**
 * Use case controller that orchestrates the view tourist details flow.
 */
public class ViewTouristDetailsController implements IViewTouristDetailsUseCase {
    private IViewTouristDetailsUseCase useCase;

    public ViewTouristDetailsController(IViewTouristDetailsUseCase useCase) {
        this.useCase = useCase;
    }

    @Override
    public TouristDTO execute(String selectedTouristId) {
        try {
            // Delegate to the use case
            return useCase.execute(selectedTouristId);
        } catch (TouristNotFoundException e) {
            // Rethrow for the UI to handle
            throw e;
        }
    }
}