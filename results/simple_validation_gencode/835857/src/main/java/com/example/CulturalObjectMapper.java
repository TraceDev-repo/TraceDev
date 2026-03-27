package com.example;

import java.util.Date;

/**
 * Mapper for converting between DTOs and entities.
 */
public class CulturalObjectMapper {
    /**
     * Converts a request to an entity.
     */
    public CulturalObject toEntity(CreateCulturalObjectRequest request) {
        CulturalObject entity = new CulturalObject();
        entity.setName(request.getName());
        entity.setType(request.getType());
        entity.setLocation(request.getLocation());
        entity.setDescription(request.getDescription());
        // ID and dates are set elsewhere.
        return entity;
    }

    /**
     * Converts an entity to a response.
     */
    public CulturalObjectResponse toResponse(CulturalObject culturalObject) {
        CulturalObjectResponse response = new CulturalObjectResponse();
        response.setId(culturalObject.getId());
        response.setName(culturalObject.getName());
        response.setMessage("Cultural object '" + culturalObject.getName() + "' created successfully.");
        // Ensure notification details are included from entity
        response.setNotificationDetails(culturalObject.getNotificationDetails());
        return response;
    }

    /**
     * Converts an entity to a form (for editing/display).
     * If entity is null, returns an empty form.
     */
    public CulturalObjectForm toForm(CulturalObject culturalObject) {
        CulturalObjectForm form = new CulturalObjectForm();
        if (culturalObject != null) {
            form.setName(culturalObject.getName());
            form.setType(culturalObject.getType());
            form.setLocation(culturalObject.getLocation());
            form.setDescription(culturalObject.getDescription());
        }
        return form;
    }

    /**
     * Converts a form to a request.
     */
    public CreateCulturalObjectRequest fromForm(CulturalObjectForm form) {
        CreateCulturalObjectRequest request = new CreateCulturalObjectRequest();
        request.setName(form.getName());
        request.setType(form.getType());
        request.setLocation(form.getLocation());
        request.setDescription(form.getDescription());
        return request;
    }
}