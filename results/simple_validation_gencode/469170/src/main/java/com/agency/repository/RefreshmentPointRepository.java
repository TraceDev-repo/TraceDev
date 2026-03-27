package com.agency.repository;

import com.agency.dto.SearchCriteria;
import com.agency.entity.RefreshmentPoint;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for refreshment point data access.
 */
public interface RefreshmentPointRepository {
    List<RefreshmentPoint> findAll(SearchCriteria searchCriteria);
    Optional<RefreshmentPoint> findById(String id);
}