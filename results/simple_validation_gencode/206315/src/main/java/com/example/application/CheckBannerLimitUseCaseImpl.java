package com.example.application;

import com.example.domain.Convention;
import com.example.domain.RefreshmentPoint;
import com.example.infrastructure.ConventionRepository;
import com.example.infrastructure.NotificationDisplayService;
import com.example.infrastructure.StateRecoveryService;
import com.example.infrastructure.NotificationService;

import java.util.Optional;

/**
 * Implementation of the CheckBannerLimit use case.
 * Follows the sequence diagram interactions.
 */
public class CheckBannerLimitUseCaseImpl implements CheckBannerLimitUseCase {
    private ConventionRepository conventionRepository;
    private NotificationDisplayService notificationDisplayService;
    private StateRecoveryService stateRecoveryService;
    private NotificationService notificationService;

    public CheckBannerLimitUseCaseImpl(ConventionRepository conventionRepository,
                                       NotificationDisplayService notificationDisplayService,
                                       StateRecoveryService stateRecoveryService,
                                       NotificationService notificationService) {
        this.conventionRepository = conventionRepository;
        this.notificationDisplayService = notificationDisplayService;
        this.stateRecoveryService = stateRecoveryService;
        this.notificationService = notificationService;
    }

    @Override
    public CheckBannerLimitResult execute(CheckBannerLimitCommand command) {
        // State recovery mechanism as per sequence diagram
        stateRecoveryService.restorePreviousState();

        // Find convention
        Optional<Convention> conventionOpt = conventionRepository.findById(command.getConventionId());
        if (!conventionOpt.isPresent()) {
            return new CheckBannerLimitResult(false, "Convention not found", 0, 0);
        }
        Convention convention = conventionOpt.get();

        // Get refreshment point from convention (aggregation path)
        Optional<RefreshmentPoint> refreshmentPointOpt = getRefreshmentPointById(convention, command.getRefreshmentPointId());
        if (!refreshmentPointOpt.isPresent()) {
            return new CheckBannerLimitResult(false, "Refreshment point not found", 0, 0);
        }
        RefreshmentPoint refreshmentPoint = refreshmentPointOpt.get();

        int count = refreshmentPoint.getBannerCount();
        int maxAllowed = refreshmentPoint.getMaxAllowedBanners();

        if (count >= maxAllowed) {
            // Send notification and display as per sequence diagram
            String notificationId = notificationService.sendNotification("Maximum banners reached");
            notificationDisplayService.displayNotification(notificationId, "Maximum banners reached");
            // Return result with notificationId
            return new CheckBannerLimitResult(false, "Max banners reached", count, maxAllowed, notificationId);
        } else {
            return new CheckBannerLimitResult(true, "Can add more banners", count, maxAllowed);
        }
    }

    /**
     * Helper method to get refreshment point by id from a convention.
     * This corresponds to the "Get Refreshment Point" ref in sequence diagram.
     */
    private Optional<RefreshmentPoint> getRefreshmentPointById(Convention convention, String refreshmentPointId) {
        return convention.getRefreshmentPoints().stream()
                .filter(rp -> rp.getId().equals(refreshmentPointId))
                .findFirst();
    }
}