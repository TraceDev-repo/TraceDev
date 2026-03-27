package com.example.interfaces;

import com.example.application.dto.TouristDTO;
import com.example.application.Response;
import java.util.List;

/**
 * Controller interface for tourist management operations.
 */
public interface TouristController {
    List<TouristDTO> searchTourists();
    TouristDTO getTouristForEdit(String touristId);
    Response updateTourist(String touristId, TouristDTO dto);
    Response confirmUpdate(String touristId, TouristDTO dto);
}