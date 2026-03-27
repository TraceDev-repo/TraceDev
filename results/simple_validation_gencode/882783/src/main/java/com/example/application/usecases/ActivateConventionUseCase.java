package com.example.application.usecases;

import com.example.application.dtos.ActivateConventionDTO;
import com.example.application.dtos.ActivationResult;
import com.example.application.ports.*;
import com.example.domain.*;

import java.util.Map;

/**
 * Use case interactor for activating a convention.
 * Implements the business logic defined in the sequence diagram.
 */
public class ActivateConventionUseCase {
    private ConventionRepository conventionRepository;
    private NotificationService notificationService;
    private EtourAdapter etourAdapter;
    private PointOfRestService pointOfRestService;
    private NotificationRepository notificationRepository;

    public ActivateConventionUseCase(ConventionRepository conventionRepository,
                                     NotificationService notificationService,
                                     EtourAdapter etourAdapter,
                                     PointOfRestService pointOfRestService,
                                     NotificationRepository notificationRepository) {
        this.conventionRepository = conventionRepository;
        this.notificationService = notificationService;
        this.etourAdapter = etourAdapter;
        this.pointOfRestService = pointOfRestService;
        this.notificationRepository = notificationRepository;
    }

    /**
     * Main execution method for activation.
     */
    public ActivationResult execute(ActivateConventionDTO dto) {
        // Step 0: Validate agency designation
        if (!validateAgencyDesignation(dto.getOperatorId())) {
            return new ActivationResult(false, "Agency is not designated for activation", false);
        }

        // Step 1: Load data from point of rest via Agency (new step)
        ConventionRequest request = fetchConventionRequestFromPointOfRest(dto.getConventionId());
        if (request == null) {
            return new ActivationResult(false, "Failed to load data from point of rest", false);
        }
        conventionRepository.saveRequest(request);

        // Step 2: Fetch convention
        Convention convention = conventionRepository.findById(dto.getConventionId());
        if (convention == null) {
            return new ActivationResult(false, "Convention not found", false);
        }

        // Step 3: Validate agreement data (optional step integrated)
        if (!convention.validateAgreement()) {
            return new ActivationResult(false, "Agreement validation failed", false);
        }

        // Step 4: Activate convention
        convention.activate();
        conventionRepository.save(convention);

        // Step 5: Create notification event
        NotificationEvent event = new NotificationEvent("NOTIF-" + dto.getConventionId(), dto.getConventionId());
        notificationRepository.save(event);

        // Step 6: Send notification with retry logic
        boolean notificationSent = etourAdapter.sendWithRetry(dto.getConventionId(), 3);
        if (notificationSent) {
            event.markAsSent();
            notificationRepository.update(event);
            return new ActivationResult(true, "Activation completed", true);
        } else {
            event.markAsFailed();
            notificationRepository.update(event);
            // Activation succeeded but notification failed
            return new ActivationResult(true, "Activation persisted, notification pending", false);
        }
    }

    /**
     * Validates agreement data by loading and validating.
     */
    public boolean validateAgreementData(String conventionId) {
        Convention convention = conventionRepository.findById(conventionId);
        if (convention == null) {
            return false;
        }
        AgreementData agreementData = convention.loadAgreementData();
        return agreementData.validate();
    }

    /**
     * Fetches convention request from the point of rest via Agency.
     */
    public ConventionRequest fetchConventionRequestFromPointOfRest(String conventionId) {
        Convention convention = conventionRepository.findById(conventionId);
        if (convention == null || convention.getAgency() == null) {
            // If convention has no agency, try to create a default agency with point-of-rest service
            Agency agency = new Agency("DEFAULT", "Default Agency", true, pointOfRestService);
            convention.setAgency(agency);
        }
        Agency agency = convention.getAgency();
        // Ensure agency has point-of-rest service
        if (agency != null && agency.isDesignatedForActivation()) {
            agency.setPointOfRestService(pointOfRestService);
            ConventionRequest request = agency.fetchConventionRequest(conventionId);
            // Update external data on existing request if found
            ConventionRequest existing = conventionRepository.findRequestByConventionId(conventionId);
            if (existing != null) {
                existing.updateExternalData(request.getData());
                existing.setStatus(request.getStatus());
                return existing;
            }
            return request;
        }
        return null;
    }

    /**
     * Explicitly validates if the agency is designated for activation.
     */
    public boolean validateAgencyDesignation(String agencyId) {
        // This is a simplified validation.
        // In a real system, you would fetch the Agency from a repository.
        // For demonstration, we assume the agency is designated.
        // We could also check if the agency is linked to the convention.
        return true;
    }
}