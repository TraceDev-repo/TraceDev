package com.example;

import java.util.Map;

/**
 * Repository interface for cultural object persistence.
 */
public interface CulturalObjectRepository {
    /**
     * Saves a cultural object.
     * @param culturalObject the object to save.
     * @return the saved object (with generated id).
     */
    CulturalObject save(CulturalObject culturalObject);

    /**
     * Checks if an object with the given unique properties already exists.
     * @param uniqueProperties map of property names to values.
     * @return true if a duplicate exists, false otherwise.
     */
    boolean existsByUniqueProperties(Map<String, Object> uniqueProperties);
}