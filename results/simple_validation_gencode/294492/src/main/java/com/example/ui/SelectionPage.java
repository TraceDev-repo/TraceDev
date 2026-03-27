package com.example.ui;

import com.example.web.controller.InsertBannerController;
import com.example.web.dto.RefreshmentPointDto;
import java.util.List;

/**
 * UI component for selecting a refreshment point.
 * This is a simplified representation; in a real app this would be a GUI class.
 */
public class SelectionPage {
    private List<RefreshmentPointDto> refreshmentPoints;

    public void displayRefreshmentPoints(List<RefreshmentPointDto> points) {
        this.refreshmentPoints = points;
        // In a real UI this would update the view.
        System.out.println("Displaying refreshment points:");
        for (RefreshmentPointDto dto : points) {
            System.out.println(" - " + dto.getName() + " (Banners: " + dto.getCurrentBannerCount() + "/" + dto.getMaxBanners() + ")");
        }
    }

    /**
     * Simulates user selection and returns the selected point's ID.
     * In a real application this would be triggered by a UI event.
     */
    public String getSelectedPointId() {
        if (refreshmentPoints == null || refreshmentPoints.isEmpty()) {
            return null;
        }
        // For demonstration, return the first point's ID.
        return refreshmentPoints.get(0).getId();
    }
}