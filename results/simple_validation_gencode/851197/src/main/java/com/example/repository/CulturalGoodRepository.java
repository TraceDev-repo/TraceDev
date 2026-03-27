package com.example.repository;

import com.example.domain.CulturalGood;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for CulturalGood entities.
 */
public interface CulturalGoodRepository {
    Optional<CulturalGood> findById(int id);
    List<CulturalGood> findAll(); // Added to satisfy requirement REQ-SEQ-001
}