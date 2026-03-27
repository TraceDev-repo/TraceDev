package com.example.interfaces;

import com.example.application.CheckBannerLimitCommand;
import com.example.application.CheckBannerLimitResult;
import com.example.application.CheckBannerLimitUseCase;
import com.example.infrastructure.StateRecoveryService;

/**
 * Controller handling banner limit checks and notification confirmations.
 * Follows the sequence diagram interactions.
 */
public class BannerController {
    private CheckBannerLimitUseCase checkBannerLimitUseCase;
    private StateRecoveryService stateRecoveryService;

    public BannerController(CheckBannerLimitUseCase checkBannerLimitUseCase,
                            StateRecoveryService stateRecoveryService) {
        this.checkBannerLimitUseCase = checkBannerLimitUseCase;
        this.stateRecoveryService = stateRecoveryService;
    }

    public CheckBannerLimitResult checkBannerLimit(String conventionId, String refreshmentPointId) {
        CheckBannerLimitCommand command = new CheckBannerLimitCommand(conventionId, refreshmentPointId);
        return checkBannerLimitUseCase.execute(command);
    }

    public void confirmNotification(String notificationId) {
        // State recovery mechanism as per sequence diagram
        stateRecoveryService.restorePreviousState();
    }
}