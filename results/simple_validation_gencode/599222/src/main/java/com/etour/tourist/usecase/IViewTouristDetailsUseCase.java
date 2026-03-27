package com.etour.tourist.usecase;

import com.etour.tourist.dto.TouristDTO;

/**
 * Use case interface for viewing tourist details.
 */
public interface IViewTouristDetailsUseCase {
    TouristDTO execute(String selectedTouristId);
}