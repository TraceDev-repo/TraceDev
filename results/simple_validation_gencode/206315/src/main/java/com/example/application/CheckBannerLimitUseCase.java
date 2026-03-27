package com.example.application;

/**
 * Interface for the CheckBannerLimit use case.
 */
public interface CheckBannerLimitUseCase {
    CheckBannerLimitResult execute(CheckBannerLimitCommand command);
}