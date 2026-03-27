package com.etour.tourist.usecase;

import com.etour.tourist.dto.TouristDTO;
import java.util.List;

/**
 * Use case interface for searching tourists (used by the display).
 */
public interface ISearchTouristUseCase {
    List<TouristDTO> searchTourist(String criteria);
}