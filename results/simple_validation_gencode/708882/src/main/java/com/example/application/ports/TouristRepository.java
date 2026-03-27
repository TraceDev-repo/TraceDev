package com.example.application.ports;

import com.example.domain.Tourist;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Tourist entity persistence operations.
 */
public interface TouristRepository {
    List<Tourist> findAll();
    Optional<Tourist> findById(String id);
    Tourist save(Tourist tourist);
}