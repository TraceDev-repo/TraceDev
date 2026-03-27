package com.etour.service;

import com.etour.model.RefreshmentPoint;

/**
 * Service interface for managing refreshment points.
 */
public interface IRestPointService {
    /**
     * Retrieves a refreshment point by its ID.
     * @param id The point ID.
     * @return The RefreshmentPoint, or null if not found.
     */
    RefreshmentPoint getRefreshmentPointById(String id);

    /**
     * Updates an existing refreshment point.
     * @param point The updated point data.
     * @return true if update succeeded, false otherwise.
     */
    boolean updateRefreshmentPoint(RefreshmentPoint point);
}