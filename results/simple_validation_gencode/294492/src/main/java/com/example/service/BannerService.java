package com.example.service;

import com.example.web.dto.BannerDto;
import com.example.web.result.*;

/**
 * Application service interface for banner‑related operations.
 */
public interface BannerService {
    ImageValidationResult validateBannerImage(byte[] imageData, String format);
    BannerCountResult checkMaxBanners(String refreshmentPointId);
    OperationResult checkAndSaveBanner(BannerDto bannerData);
    CancellationResult cancelPendingInsertion(BannerDto bannerData);
}