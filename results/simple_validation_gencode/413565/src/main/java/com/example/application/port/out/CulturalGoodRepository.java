package com.example.application.port.out;

import com.example.domain.CulturalGood;

/**
 * Output port for cultural good persistence.
 */
public interface CulturalGoodRepository {
    CulturalGood findById(String id);
    CulturalGood save(CulturalGood culturalGood);
}