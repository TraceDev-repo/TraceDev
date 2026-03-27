package com.example;

/**
 * Service interface for cultural object operations.
 */
public interface CulturalObjectService {
    /**
     * Creates a new cultural object.
     * @param request the creation request.
     * @return the created cultural object.
     * @throws ValidationException if validation fails.
     */
    CulturalObject createCulturalObject(CreateCulturalObjectRequest request);
}