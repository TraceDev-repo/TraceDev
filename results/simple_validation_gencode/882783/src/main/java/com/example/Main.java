package com.example;

import com.example.application.dtos.ActivateConventionDTO;
import com.example.application.dtos.ActivationResult;
import com.example.application.usecases.ActivateConventionUseCase;
import com.example.infrastructure.etour.EtourRestAdapter;
import com.example.infrastructure.notification.EmailNotificationService;
import com.example.infrastructure.persistence.ConventionRepositoryImpl;
import com.example.infrastructure.persistence.NotificationRepositoryImpl;
import com.example.infrastructure.pointofrest.PointOfRestServiceImpl;
import com.example.presentation.ConventionController;

/**
 * Main class to demonstrate the activation flow.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Convention Activation Demo ===");

        // Setup dependencies (IoC container would do this in a real app)
        ConventionRepositoryImpl conventionRepo = new ConventionRepositoryImpl();
        EmailNotificationService notificationService = new EmailNotificationService();
        EtourRestAdapter etourAdapter = new EtourRestAdapter();
        PointOfRestServiceImpl pointOfRestService = new PointOfRestServiceImpl();
        NotificationRepositoryImpl notificationRepo = new NotificationRepositoryImpl();

        // Create use case with all adapters
        ActivateConventionUseCase useCase = new ActivateConventionUseCase(
                conventionRepo,
                notificationService,
                etourAdapter,
                pointOfRestService,
                notificationRepo
        );

        // Create controller
        ConventionController controller = new ConventionController(useCase);

        // Simulate activation
        ActivateConventionDTO dto = new ActivateConventionDTO("CONV-123", "OP-456", "RP-789");
        ActivationResult result = controller.activateConvention(dto);

        System.out.println("Activation Result:");
        System.out.println("Success: " + result.isSuccess());
        System.out.println("Message: " + result.getMessage());
        System.out.println("Notification Sent: " + result.isNotificationSent());
    }
}