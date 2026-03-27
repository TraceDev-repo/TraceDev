package com.example.application;

import com.example.domain.RefreshmentPoint;

/**
 * Repository interface (dependency inversion) for refreshment points.
 */
public interface IRefreshmentPointRepository {
    /**
     * Finds a refreshment point by ID (might involve cache or fallback).
     * @param id the refreshment point ID
     * @return the refreshment point entity
     */
    RefreshmentPoint findById(Long id);

    /**
     * Loads data directly from server for the given ID.
     * @param id the refreshment point ID
     * @return the refreshment point entity
     */
    RefreshmentPoint loadDataFromServer(Long id);
}