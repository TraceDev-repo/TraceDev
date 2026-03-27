package com.example.domain.repository;

import com.example.domain.CulturalHeritage;
import java.util.List;

/**
 * Repository interface for CulturalHeritage entities.
 */
public interface ICulturalHeritageRepository {
    List<CulturalHeritage> findAll();
    CulturalHeritage findById(String id);
    void delete(String id);
}