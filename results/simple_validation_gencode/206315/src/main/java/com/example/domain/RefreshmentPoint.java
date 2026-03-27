package com.example.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Refreshment Point in the domain.
 * Contains a list of banners and a maximum allowed banner count.
 */
public class RefreshmentPoint {
    private String id;
    private String name;
    private int maxAllowedBanners;
    private List<Banner> currentBanners;

    public RefreshmentPoint(String id, String name, int maxAllowedBanners) {
        this.id = id;
        this.name = name;
        this.maxAllowedBanners = maxAllowedBanners;
        this.currentBanners = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getMaxAllowedBanners() {
        return maxAllowedBanners;
    }

    public List<Banner> getCurrentBanners() {
        return currentBanners;
    }

    /**
     * Adds a banner if the limit is not yet reached.
     * @param banner the banner to add
     * @return true if banner was added, false otherwise
     */
    public boolean addBanner(Banner banner) {
        if (canAcceptMoreBanners()) {
            currentBanners.add(banner);
            return true;
        }
        return false;
    }

    public int getBannerCount() {
        return currentBanners.size();
    }

    public boolean canAcceptMoreBanners() {
        return currentBanners.size() < maxAllowedBanners;
    }
}