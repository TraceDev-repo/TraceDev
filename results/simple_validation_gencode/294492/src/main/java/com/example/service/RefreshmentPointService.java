package com.example.service;

import com.example.domain.RefreshmentPoint;
import com.example.web.dto.RefreshmentPointDto;
import java.util.List;

/**
 * Application service interface for refreshment point operations.
 */
public interface RefreshmentPointService {
    List<RefreshmentPoint> getAllRefreshmentPoints();
    RefreshmentPoint getRefreshmentPointById(String id);
}