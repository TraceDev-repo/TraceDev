package com.example.service;

import com.example.refreshmentpoint.RefreshmentPoint;
import java.util.List;
import java.util.ArrayList;

/**
 * Service for searching cultural heritage and refreshment points.
 * Simulates the related use case SearchCulturalHeritage.
 */
public class SearchCulturalHeritageService {
    private List<RefreshmentPoint> searchResults;

    public SearchCulturalHeritageService() {
        this.searchResults = new ArrayList<>();
        // Sample data
        searchResults.add(new RefreshmentPoint("RP1", "Cafe Central", "Main Square"));
        searchResults.add(new RefreshmentPoint("RP2", "Water Fountain", "Park Entrance"));
    }

    /**
     * Searches for refreshment points based on criteria.
     * @param criteria search criteria (simplified as a string for demo)
     * @return list of matching refreshment points
     */
    public List<RefreshmentPoint> searchPoints(String criteria) {
        // Simplified search: filter by name containing criteria
        List<RefreshmentPoint> result = new ArrayList<>();
        for (RefreshmentPoint point : searchResults) {
            if (point.getName().toLowerCase().contains(criteria.toLowerCase())) {
                result.add(point);
            }
        }
        return result;
    }

    /**
     * Returns all current search results.
     * @return list of refreshment points from last search
     */
    public List<RefreshmentPoint> getSearchResults() {
        return new ArrayList<>(searchResults);
    }

    /**
     * Simulates setting search results (for testing).
     */
    public void setSearchResults(List<RefreshmentPoint> results) {
        this.searchResults = new ArrayList<>(results);
    }
}