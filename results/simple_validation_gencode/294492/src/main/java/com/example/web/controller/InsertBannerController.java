package com.example.web.controller;

import com.example.service.BannerService;
import com.example.service.RefreshmentPointService;
import com.example.service.SearchRefreshmentPointService;
import com.example.web.dto.BannerDto;
import com.example.web.dto.RefreshmentPointDto;
import com.example.web.form.BannerInsertionForm;
import com.example.web.form.ConfirmationRequest;
import com.example.web.result.*;

import java.util.List;

/**
 * Controller for the "Insert New Banner" use case.
 * Coordinates the flow as described in the sequence diagram.
 */
public class InsertBannerController {
    private BannerService bannerService;
    private RefreshmentPointService refreshmentPointService;
    private SearchRefreshmentPointService searchRefreshmentPointService;

    // Constructor as per class diagram
    public InsertBannerController(BannerService bannerService,
                                  RefreshmentPointService refreshmentPointService,
                                  SearchRefreshmentPointService searchRefreshmentPointService) {
        this.bannerService = bannerService;
        this.refreshmentPointService = refreshmentPointService;
        this.searchRefreshmentPointService = searchRefreshmentPointService;
    }

    /**
     * Step 1: Retrieve refreshment points (integrates with SearchRefreshmentPoint use case).
     */
    public List<RefreshmentPointDto> getRefreshmentPoints() {
        return searchRefreshmentPointService.getAllRefreshmentPoints();
    }

    /**
     * Step 2 & 3: Initiate banner insertion for a selected point.
     * Called from UI after user selects a refreshment point.
     */
    public BannerInsertionForm initiateBannerInsertion(String refreshmentPointId) {
        com.example.domain.RefreshmentPoint point = refreshmentPointService.getRefreshmentPointById(refreshmentPointId);
        if (point == null) {
            throw new IllegalArgumentException("Refreshment point not found: " + refreshmentPointId);
        }
        int currentCount = point.getCurrentBannerCount();
        int maxBanners = point.getMaxBanners();
        return new BannerInsertionForm(refreshmentPointId, point.getName(), currentCount, maxBanners);
    }

    /**
     * Step 6: Upload and validate the selected banner image.
     * This corresponds to sequence diagram step 18.
     */
    public ValidationResult uploadAndValidateBanner(BannerDto bannerData) {
        // Step 7-8: Validate image and check max banners (via BannerService).
        ImageValidationResult imageValidation = bannerService.validateBannerImage(bannerData.getImageData(), bannerData.getImageFormat());
        if (!imageValidation.hasPassed()) {
            return ValidationResult.failure("Image validation failed: " + String.join(", ", imageValidation.getErrors()));
        }

        BannerCountResult countResult = bannerService.checkMaxBanners(bannerData.getRefreshmentPointId());
        if (!countResult.canAcceptBanner()) {
            String errorMsg = countResult.getMessage();
            if (errorMsg.contains("not found")) {
                return ValidationResult.failure("Refreshment point not found.");
            } else {
                return ValidationResult.failure("Maximum banners reached.");
            }
        }

        // Step 9: Create confirmation request.
        ConfirmationRequest confirmation = new ConfirmationRequest("Confirm insertion of banner?", bannerData);
        return ValidationResult.success(confirmation);
    }

    /**
     * Step 10: Confirm the insertion and save the banner.
     * Called when user confirms the operation (sequence diagram step 51).
     */
    public OperationResult confirmInsertion(BannerDto bannerData) {
        return bannerService.checkAndSaveBanner(bannerData);
    }

    /**
     * Alternative flow: User cancels the operation before confirmation.
     * Sequence diagram step 44.
     */
    public CancellationResult cancelInsertion(BannerDto bannerData) {
        CancellationResult result = bannerService.cancelPendingInsertion(bannerData);
        // Controller-level cleanup (e.g., clear session data)
        // For simplicity, we just return the result.
        return result;
    }
}