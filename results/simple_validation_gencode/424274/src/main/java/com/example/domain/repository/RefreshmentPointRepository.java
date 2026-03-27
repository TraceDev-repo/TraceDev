package com.example.domain.repository;

import com.example.domain.RefreshmentPoint;
import com.example.domain.SearchSpecification;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for RefreshmentPoint domain entities.
 * This interface belongs to the Domain Layer.
 */
public interface RefreshmentPointRepository {
    /**
     * Finds all refreshment points matching the given search specification.
     *
     * @param spec the search specification
     * @return a list of matching refreshment points
     */
    List<RefreshmentPoint> findAllBySpecification(SearchSpecification spec);

    /**
     * Finds a refreshment point by its unique identifier.
     *
     * @param id the id of the refreshment point
     * @return an Optional containing the refreshment point if found
     */
    Optional<RefreshmentPoint> findById(String id);
}