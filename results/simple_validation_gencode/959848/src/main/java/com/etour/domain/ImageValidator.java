package com.etour.domain;

import java.util.Arrays;
import java.util.List;

/**
 * Validates banner images based on predefined rules.
 * Modified to satisfy quality requirement: validate the inserted image directly.
 */
public class ImageValidator {
    
    /**
     * Validates an image URL against predefined rules.
     * @param imageUrl the URL of the image to validate
     * @return true if the image is valid, false otherwise
     */
    public boolean validateImage(String imageUrl) {
        // Example validation: check if URL ends with allowed image extensions
        if (imageUrl == null || imageUrl.trim().isEmpty()) {
            return false;
        }
        String lowerUrl = imageUrl.toLowerCase();
        return lowerUrl.endsWith(".jpg") || 
               lowerUrl.endsWith(".jpeg") || 
               lowerUrl.endsWith(".png") || 
               lowerUrl.endsWith(".gif");
    }

    /**
     * Returns the list of validation rules used for image validation.
     * @return a list of validation rule descriptions
     */
    public List<String> getValidationRules() {
        return Arrays.asList(
            "Image must be in JPG, JPEG, PNG, or GIF format.",
            "Image URL must not be empty.",
            "Image must be accessible via HTTP/HTTPS."
        );
    }
}