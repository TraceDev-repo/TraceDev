package com.example.service;

import com.example.domain.Banner;
import com.example.domain.RefreshmentPoint;
import com.example.infrastructure.BannerRepository;
import com.example.infrastructure.RefreshmentPointRepository;
import com.example.infrastructure.ImageValidator;
import com.example.web.dto.BannerDto;
import com.example.web.result.*;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

/**
 * Implementation of BannerService containing the core business logic.
 */
public class BannerServiceImpl implements BannerService {
    private BannerRepository bannerRepository;
    private RefreshmentPointRepository refreshmentPointRepository;
    private ImageValidator imageValidator;

    public BannerServiceImpl(BannerRepository bannerRepository,
                             RefreshmentPointRepository refreshmentPointRepository,
                             ImageValidator imageValidator) {
        this.bannerRepository = bannerRepository;
        this.refreshmentPointRepository = refreshmentPointRepository;
        this.imageValidator = imageValidator;
    }

    @Override
    public ImageValidationResult validateBannerImage(byte[] imageData, String format) {
        // Delegate validation to ImageValidator as per sequence diagram steps 20‑27.
        return imageValidator.validateAllCharacteristics(imageData, format);
    }

    @Override
    public BannerCountResult checkMaxBanners(String refreshmentPointId) {
        // Sequence diagram steps 29‑38: find point and count existing banners.
        Optional<RefreshmentPoint> pointOpt = refreshmentPointRepository.findById(refreshmentPointId);
        if (pointOpt.isEmpty()) {
            return BannerCountResult.cannotAccept("Refreshment point not found.");
        }
        RefreshmentPoint point = pointOpt.get();
        int currentCount = point.getCurrentBannerCount();
        boolean canAccept = currentCount < point.getMaxBanners();
        if (canAccept) {
            return BannerCountResult.canAccept(currentCount, point.getMaxBanners());
        } else {
            return BannerCountResult.cannotAccept("Maximum banners reached.");
        }
    }

    @Override
    public OperationResult checkAndSaveBanner(BannerDto bannerData) {
        // Sequence diagram steps 52‑58: create and save banner entity.
        // First, verify the point still exists and has capacity.
        Optional<RefreshmentPoint> pointOpt = refreshmentPointRepository.findById(bannerData.getRefreshmentPointId());
        if (pointOpt.isEmpty()) {
            return OperationResult.failure("Refreshment point not found.");
        }
        RefreshmentPoint point = pointOpt.get();
        if (!point.canAcceptNewBanner()) {
            return OperationResult.failure("Cannot save banner: " + point.getCurrentBannerCount() + "/" + point.getMaxBanners() + " banners already exist.");
        }

        // Create Banner entity.
        Banner banner = new Banner();
        banner.setId(UUID.randomUUID().toString());
        // Generate a file path (simplified)
        String filePath = "/uploads/banners/" + banner.getId() + "." + bannerData.getImageFormat();
        banner.setImageFilePath(filePath);
        banner.setImageSize(bannerData.getImageData().length);
        banner.setImageFormat(bannerData.getImageFormat());
        banner.setCreationDate(new Date());
        banner.setRefreshmentPoint(point);

        // Save via repository and add to point
        bannerRepository.save(banner);
        point.addBanner(banner);

        return OperationResult.success("Banner inserted successfully.");
    }

    @Override
    public CancellationResult cancelPendingInsertion(BannerDto bannerData) {
        // Sequence diagram step 46: cleanup temporary resources.
        // In a real scenario we might delete any temporarily uploaded files.
        // For demonstration, we simulate cleanup.
        boolean cleanupPerformed = false;
        // Simulate file cleanup if image data exists
        if (bannerData.getImageData() != null && bannerData.getImageData().length > 0) {
            cleanupPerformed = true;
        }
        return CancellationResult.success(cleanupPerformed);
    }
}