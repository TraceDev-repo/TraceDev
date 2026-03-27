package com.example.infrastructure;

import com.example.domain.CulturalObject;
import java.util.List;

/**
 * Repository interface for cultural objects.
 */
public interface CulturalObjectRepository {
    List<CulturalObject> searchByCriteria(SearchCriteria criteria);
    List<CulturalObject> findByKeyword(String keyword);
    boolean hasData();
    boolean checkConnection();
    boolean checkDataAvailability();
}