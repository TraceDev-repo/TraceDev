package com.example;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of the CulturalObjectService.
 * Orchestrates the creation process including validation, mapping, persistence, and notification.
 */
public class CulturalObjectServiceImpl implements CulturalObjectService {
    private CulturalObjectValidator validator;
    private CulturalObjectRepository repository;
    private CulturalObjectMapper mapper;
    private NotificationService notificationService;

    public CulturalObjectServiceImpl(CulturalObjectValidator validator,
                                     CulturalObjectRepository repository,
                                     CulturalObjectMapper mapper,
                                     NotificationService notificationService) {
        this.validator = validator;
        this.repository = repository;
        this.mapper = mapper;
        this.notificationService = notificationService;
    }

    @Override
    public CulturalObject createCulturalObject(CreateCulturalObjectRequest request) {
        // Step 1: Validate the request
        validator.validateForCreation(request);

        // Step 2: Map request to entity
        CulturalObject culturalObject = mapper.toEntity(request);
        // Set creation and update timestamps
        Date now = new Date();
        culturalObject.setCreationDate(now);
        culturalObject.setLastUpdatedDate(now);

        // Step 3: Save to repository
        CulturalObject savedCulturalObject = repository.save(culturalObject);

        // Step 4: Generate success notification message
        String notificationMessage = notificationService.generateSuccessMessage(savedCulturalObject);

        // Step 5: Send notification
        notificationService.notifySuccess(savedCulturalObject);

        // Step 6: Set notification details in the entity (for response mapping)
        savedCulturalObject.setNotificationDetails(notificationMessage);

        return savedCulturalObject;
    }
}