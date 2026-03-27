package com.etour.repository;

import com.etour.model.RefreshmentPoint;

/**
 * Repository interface for RefreshmentPoint persistence.
 */
public interface IRefreshmentPointRepository {
    /**
     * Finds a refreshment point by ID.
     * @param id The point ID.
     * @return The RefreshmentPoint, or null if not found.
     */
    RefreshmentPoint findById(String id);

    /**
     * Saves (updates) a refreshment point.
     * @param point The point to save.
     * @return true if save succeeded, false otherwise.
     */
    boolean save(RefreshmentPoint point);
}