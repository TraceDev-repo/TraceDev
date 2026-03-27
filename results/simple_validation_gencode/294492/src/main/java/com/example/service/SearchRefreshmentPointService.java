package com.example.service;

import com.example.web.dto.RefreshmentPointDto;
import java.util.List;

/**
 * Integration interface for the SearchRefreshmentPoint use case.
 * Provides a list of refreshment points with DTOs (as per sequence diagram step 1‑10).
 */
public interface SearchRefreshmentPointService {
    List<RefreshmentPointDto> getAllRefreshmentPoints();
}