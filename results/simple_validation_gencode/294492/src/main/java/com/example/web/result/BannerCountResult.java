package com.example.web.result;

/**
 * Result of checking banner capacity for a refreshment point.
 */
public class BannerCountResult {
    private boolean canAcceptBanner;
    private int currentCount;
    private int maxBanners;
    private String message;

    private BannerCountResult(boolean canAcceptBanner, int currentCount, int maxBanners, String message) {
        this.canAcceptBanner = canAcceptBanner;
        this.currentCount = currentCount;
        this.maxBanners = maxBanners;
        this.message = message;
    }

    public static BannerCountResult canAccept(int currentCount, int maxBanners) {
        return new BannerCountResult(true, currentCount, maxBanners, "Can accept new banner.");
    }

    public static BannerCountResult cannotAccept(String reason) {
        return new BannerCountResult(false, 0, 0, reason);
    }

    public boolean canAcceptBanner() {
        return canAcceptBanner;
    }

    public int getCurrentCount() {
        return currentCount;
    }

    public int getMaxBanners() {
        return maxBanners;
    }

    public String getMessage() {
        return message;
    }
}