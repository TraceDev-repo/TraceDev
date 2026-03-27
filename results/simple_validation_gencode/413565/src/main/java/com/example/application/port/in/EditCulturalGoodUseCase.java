package com.example.application.port.in;

import com.example.domain.CulturalGood;
import java.util.Map;

/**
 * Input port for editing cultural goods.
 */
public interface EditCulturalGoodUseCase {
    CulturalGood loadCulturalGood(String id);
    boolean updateCulturalGood(String id, Map<String, Object> updatedData);
    boolean requestConfirmation(String operation);
}