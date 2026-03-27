package com.example;

import com.example.application.CheckBannerLimitUseCase;
import com.example.application.CheckBannerLimitUseCaseImpl;
import com.example.infrastructure.*;
import com.example.interfaces.BannerController;

/**
 * Main class to demonstrate the runnable system.
 */
public class Main {
    public static void main(String[] args) {
        // Setup infrastructure components
        ConventionRepository conventionRepository = new ConventionRepositoryImpl();
        NotificationRepository notificationRepository = new NotificationRepositoryImpl();
        NotificationService notificationService = new NotificationService(notificationRepository);
        UIAdapter uiAdapter = new WebSocketUIAdapterImpl(); // Corrected dependency
        NotificationDisplayService notificationDisplayService = new NotificationDisplayServiceImpl(uiAdapter);
        StateRecoveryService stateRecoveryService = new StateRecoveryServiceImpl();

        // Setup use case
        CheckBannerLimitUseCase useCase = new CheckBannerLimitUseCaseImpl(
                conventionRepository,
                notificationDisplayService,
                stateRecoveryService,
                notificationService
        );

        // Setup controller
        BannerController controller = new BannerController(useCase, stateRecoveryService);

        // Simulate a check banner limit call
        System.out.println("=== Checking Banner Limit ===");
        var result = controller.checkBannerLimit("conv1", "rp1");
        System.out.println("Result: " + result.getMessage());
        System.out.println("Valid: " + result.isValid());
        System.out.println("Current count: " + result.getCurrentCount());
        System.out.println("Max allowed: " + result.getMaxAllowed());
        String notificationId = result.getNotificationId();
        if (notificationId != null) {
            System.out.println("Notification ID: " + notificationId);
            // Simulate confirmation
            controller.confirmNotification(notificationId);
        }
    }
}