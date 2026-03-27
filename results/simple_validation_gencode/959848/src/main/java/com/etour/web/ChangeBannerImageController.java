package com.etour.web;

import com.etour.domain.Banner;
import com.etour.domain.TurningPoint;
import com.etour.infrastructure.ErrorHandler;
import com.etour.infrastructure.NetworkConnectionManager;
import com.etour.service.ChangeBannerImageService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controller for the Change Banner Image use case.
 * Modified to separate image selection and change request submission.
 */
public class ChangeBannerImageController {
    private ChangeBannerImageService changeBannerImageService;
    private ErrorHandler errorHandler;
    private NetworkConnectionManager networkConnectionManager;
    
    public ChangeBannerImageController(
            ChangeBannerImageService changeBannerImageService,
            ErrorHandler errorHandler,
            NetworkConnectionManager networkConnectionManager) {
        this.changeBannerImageService = changeBannerImageService;
        this.errorHandler = errorHandler;
        this.networkConnectionManager = networkConnectionManager;
    }
    
    /**
     * Displays turning points for the logged-in operator.
     * @param operatorId the ID of the logged-in operator
     * @return a list of maps with turning point details
     */
    public List<Map<String, String>> displayTurningPoints(String operatorId) {
        List<TurningPoint> points = changeBannerImageService.searchTurningPoints(operatorId);
        List<Map<String, String>> result = new ArrayList<>();
        for (TurningPoint tp : points) {
            Map<String, String> map = new HashMap<>();
            map.put("id", tp.getId());
            map.put("name", tp.getName());
            map.put("location", tp.getLocation());
            result.add(map);
        }
        return result;
    }
    
    /**
     * Displays banners for a selected turning point.
     * @param turningPointId the ID of the turning point
     * @return a list of BannerDTO objects
     */
    public List<BannerDTO> displayBanners(String turningPointId) {
        List<Banner> banners = changeBannerImageService.getBannersForTurningPoint(turningPointId);
        List<BannerDTO> dtos = new ArrayList<>();
        for (Banner banner : banners) {
            BannerDTO dto = new BannerDTO(
                banner.getId(),
                banner.getName(),
                banner.getImageUrl()
            );
            dtos.add(dto);
        }
        return dtos;
    }
    
    /**
     * Shows the image selection form for a specific banner.
     * @param bannerId the banner ID
     * @return a BannerDTO with current banner details
     */
    public BannerDTO showImageSelectionForm(String bannerId) {
        Banner banner = changeBannerImageService.getBannerDetails(bannerId);
        if (banner == null) {
            errorHandler.handleError("BANNER_NOT_FOUND", "Banner not found: " + bannerId);
            return null;
        }
        return new BannerDTO(banner.getId(), banner.getName(), banner.getImageUrl());
    }
    
    /**
     * Submits an image selection for validation.
     * @param bannerId the banner ID
     * @param newImageUrl the new image URL
     * @return a map with validation result and message
     */
    public Map<String, Object> submitImageSelection(String bannerId, String newImageUrl) {
        Map<String, Object> result = new HashMap<>();
        boolean valid = changeBannerImageService.validateBannerImage(bannerId, newImageUrl);
        result.put("validationResult", valid);
        if (valid) {
            result.put("message", "Image valid. Submit change request?");
        } else {
            result.put("message", "Invalid image. Please choose a valid image.");
        }
        return result;
    }
    
    /**
     * Submits a change request after validation.
     * @param bannerId the banner ID
     * @param newImageUrl the new image URL
     * @return a map with operation status and message
     */
    public Map<String, Object> submitChangeRequest(String bannerId, String newImageUrl) {
        Map<String, Object> result = new HashMap<>();
        
        // Check network connection as per sequence diagram
        if (!networkConnectionManager.checkConnection()) {
            errorHandler.handleError("SERVER_CONNECTION", "Connection to ETOUR server interrupted");
            result.put("success", false);
            result.put("message", "Connection error. Please try again.");
            return result;
        }
        
        // The service should handle its own connection checking for repository operations
        boolean success = changeBannerImageService.confirmAndPersist(bannerId, newImageUrl);
        result.put("success", success);
        result.put("message", success ? "Banner modified successfully" : "Failed to modify banner");
        return result;
    }
    
    /**
     * Convenience method that combines validation and change request.
     * @param bannerId the banner ID
     * @param newImageUrl the new image URL
     * @return a status message
     */
    public String confirmImageChange(String bannerId, String newImageUrl) {
        Map<String, Object> validation = submitImageSelection(bannerId, newImageUrl);
        if (!(Boolean) validation.get("validationResult")) {
            return (String) validation.get("message");
        }
        Map<String, Object> changeResult = submitChangeRequest(bannerId, newImageUrl);
        return (String) changeResult.get("message");
    }
}