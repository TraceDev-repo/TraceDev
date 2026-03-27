package com.example.application;

import com.example.domain.TouristAccount;
import com.example.domain.AccountStatus;
import com.example.infrastructure.ITouristAccountRepository;
import com.example.infrastructure.INotificationService;
import com.example.infrastructure.IConfirmationService;
import com.example.infrastructure.PerformanceMonitor;

/**
 * Service implementing the use case for updating tourist account status.
 * Follows the flow from the sequence diagram, including performance monitoring.
 */
public class UpdateTouristAccountStatusService implements IUpdateTouristAccountStatusUseCase {

    private ITouristAccountRepository touristAccountRepository;
    private INotificationService notificationService;
    private IConfirmationService confirmationService;
    private PerformanceMonitor performanceMonitor; // Added for performance constraint validation

    public UpdateTouristAccountStatusService(ITouristAccountRepository repository,
                                             INotificationService notificationService,
                                             IConfirmationService confirmationService,
                                             PerformanceMonitor performanceMonitor) {
        this.touristAccountRepository = repository;
        this.notificationService = notificationService;
        this.confirmationService = confirmationService;
        this.performanceMonitor = performanceMonitor;
        // PerformanceMonitor is now required dependency as per design
    }

    @Override
    public void execute(UpdateTouristAccountStatusCommand command) {
        // Performance constraint validation - check timeout
        if (performanceMonitor != null) {
            performanceMonitor.checkTimeout();
        }

        // Step 2: System asks for confirmation
        boolean confirmationResult = confirmationService.requestConfirmation(
                command.getTouristAccountId(),
                command.getTargetStatus()
        );

        if (!confirmationResult) {
            // User cancelled - operation terminated
            throw new RuntimeException("Operation cancelled by user.");
        }

        // Performance constraint validation after confirmation
        if (performanceMonitor != null) {
            performanceMonitor.checkTimeout();
        }

        // Find account after confirmation
        TouristAccount account = touristAccountRepository.findById(command.getTouristAccountId())
                .orElseThrow(() -> new NotFoundException("Account not found: " + command.getTouristAccountId()));

        // Performance constraint validation before status change
        if (performanceMonitor != null) {
            performanceMonitor.checkTimeout();
        }

        // Step 3: Confirm operation - change status
        if (command.getTargetStatus() == AccountStatus.ACTIVE) {
            account.enable();
        } else {
            account.disable();
        }

        // Save updated account
        touristAccountRepository.save(account);

        // Performance constraint validation before notification
        if (performanceMonitor != null) {
            performanceMonitor.checkTimeout();
        }

        // Step 4: Notification
        notificationService.notifyStatusChange(account);

        // Final performance validation - log elapsed time
        if (performanceMonitor != null) {
            long elapsedTime = performanceMonitor.getElapsedTime();
            System.out.println("[Performance Constraint Validated] Elapsed time: " + elapsedTime + " seconds");
        }
    }

    // Inner exception for account not found
    public static class NotFoundException extends RuntimeException {
        public NotFoundException(String message) {
            super(message);
        }
    }
}