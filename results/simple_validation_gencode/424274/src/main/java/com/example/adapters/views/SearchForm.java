package com.example.adapters.views;

import com.example.application.SearchRefreshmentPointsQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the search form used by the UI.
 * This class belongs to the Interface Adapters Layer.
 */
public class SearchForm {
    private String location;
    private String type;
    private double minRating;
    private List<String> amenities;

    public SearchForm() {
        this.amenities = new ArrayList<>();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getMinRating() {
        return minRating;
    }

    public void setMinRating(double minRating) {
        this.minRating = minRating;
    }

    public List<String> getAmenities() {
        return amenities;
    }

    public void setAmenities(List<String> amenities) {
        this.amenities = amenities;
    }

    /**
     * Simulates the user filling the form (step 3 in sequence diagram).
     *
     * @param location the location filter
     * @param type the type filter
     * @param minRating the minimum rating
     * @param amenities the list of required amenities
     */
    public void fillForm(String location, String type, double minRating, List<String> amenities) {
        this.location = location;
        this.type = type;
        this.minRating = minRating;
        this.amenities = amenities;
        System.out.println("Form filled with location: " + location + ", type: " + type + ", minRating: " + minRating);
    }

    /**
     * Validates the form data.
     *
     * @return the validation result
     */
    public SearchFormValidationResult validate() {
        List<String> errors = new ArrayList<>();

        if (location == null || location.trim().isEmpty()) {
            errors.add("Location must be specified");
        }

        if (minRating < 0 || minRating > 5) {
            errors.add("Minimum rating must be between 0 and 5");
        }

        boolean success = errors.isEmpty();
        return new SearchFormValidationResult(success, errors);
    }

    /**
     * Converts the form data to a SearchRefreshmentPointsQuery object.
     *
     * @return the query object
     */
    public SearchRefreshmentPointsQuery toQuery() {
        return new SearchRefreshmentPointsQuery(location, type, minRating, amenities);
    }
}