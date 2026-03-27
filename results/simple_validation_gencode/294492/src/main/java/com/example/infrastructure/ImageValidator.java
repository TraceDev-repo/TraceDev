package com.example.infrastructure;

import com.example.web.result.ImageValidationResult;
import java.util.ArrayList;
import java.util.List;

/**
 * Validates image characteristics (size, format, etc.).
 * Used by BannerServiceImpl as per sequence diagram.
 */
public class ImageValidator {
    private static final long MAX_SIZE_BYTES = 10 * 1024 * 1024; // 10 MB
    private static final String[] ALLOWED_FORMATS = {"jpg", "jpeg", "png", "gif"};

    public boolean validateSize(long sizeBytes) {
        return sizeBytes <= MAX_SIZE_BYTES;
    }

    public boolean validateFormat(String format) {
        if (format == null) return false;
        String lower = format.toLowerCase();
        for (String allowed : ALLOWED_FORMATS) {
            if (lower.contains(allowed)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Combined validation as per sequence diagram step 24‑26.
     */
    public ImageValidationResult validateAllCharacteristics(byte[] imageData, String format) {
        List<String> errors = new ArrayList<>();
        if (imageData == null || imageData.length == 0) {
            errors.add("Image data is empty.");
        } else if (!validateSize(imageData.length)) {
            errors.add("Image size exceeds maximum allowed.");
        }
        if (!validateFormat(format)) {
            errors.add("Image format not supported.");
        }
        // Additional checks (resolution, aspect ratio, content type) could be added here.
        if (errors.isEmpty()) {
            return ImageValidationResult.passed();
        } else {
            return ImageValidationResult.failed(errors);
        }
    }
}