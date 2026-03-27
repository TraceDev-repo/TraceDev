package com.etour.tourist.repository;

import com.etour.tourist.domain.Tourist;

/**
 * Repository interface for tourist data access.
 */
public interface ITouristRepository {
    Tourist findById(String id);
}