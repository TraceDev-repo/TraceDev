package com.etour.service;

import com.etour.agency.AgencyOperator;
import com.etour.domain.Banner;
import com.etour.domain.TurningPoint;
import com.etour.domain.ImageValidator;
import com.etour.infrastructure.ErrorHandler;
import com.etour.infrastructure.NotificationService;
import com.etour.exceptions.AuthenticationException;
import com.etour.repository.BannerRepository;
import java.util.List;

/**
 * Main service for changing a banner's image.
 * Modified to include NotificationService dependency for success notifications.
 * Uses SearchRefreshmentPointService for integration.
 */
public class ChangeBannerImageService {
    private BannerRepository bannerRepository;
    private ImageValidator imageValidator;
    private ErrorHandler errorHandler;
    private NotificationService notificationService;
    private SearchRefreshmentPointService searchService;
    
    public ChangeBannerImageService(
            BannerRepository bannerRepository,
            ImageValidator imageValidator,
            ErrorHandler errorHandler,
            NotificationService notificationService,
            SearchRefreshmentPointService searchService) {
        this.bannerRepository = bannerRepository;
        this.imageValidator = imageValidator;
        this.errorHandler = errorHandler;
        this.notificationService = notificationService;
        this.searchService = searchService;
    }
    
    /**
     * Searches turning points available to the given operator.
     * @param operatorId the ID of the logged-in operator
     * @return list of TurningPoint objects
     */
    public List<TurningPoint> searchTurningPoints(String operatorId) {
        // Authenticate the operator 
        AgencyOperator operator = authenticateOperator(operatorId);
        if (operator == null) {
            throw new AuthenticationException("OPERATOR_NOT_AUTHENTICATED", "Operator not authenticated: " + operatorId);
        }
        // Use the integrated search service
        return searchService.findTurningPointsByOperator(operatorId);
    }
    
    /**
     * Gets all banners for a specific turning point.
     * @param turningPointId the ID of the turning point
     * @return list of Banner objects
     */
    public List<Banner> getBannersForTurningPoint(String turningPointId) {
        try {
            return bannerRepository.findBannersByTurningPointId(turningPointId);
        } catch (Exception e) {
            errorHandler.handleError("REPOSITORY_ERROR", "Failed to fetch banners: " + e.getMessage());
            return List.of();
        }
    }
    
    /**
     * Retrieves details of a specific banner.
     * @param bannerId the banner's ID
     * @return the Banner object, or null if not found
     */
    public Banner getBannerDetails(String bannerId) {
        try {
            return bannerRepository.findBannerById(bannerId);
        } catch (Exception e) {
            errorHandler.handleError("REPOSITORY_ERROR", "Failed to fetch banner details: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Validates a new image URL for a banner.
     * @param bannerId the banner ID
     * @param newImageUrl the proposed new image URL
     * @return true if the image is valid, false otherwise
     */
    public boolean validateBannerImage(String bannerId, String newImageUrl) {
        Banner banner = getBannerDetails(bannerId);
        if (banner == null) {
            return false;
        }
        boolean valid = imageValidator.validateImage(newImageUrl);
        if (!valid) {
            errorHandler.handleError("INVALID_IMAGE", "Image validation failed for URL: " + newImageUrl);
        }
        return valid;
    }
    
    /**
     * Confirms the change and persists the updated banner.
     * @param bannerId the banner ID
     * @param newImageUrl the new image URL (already validated)
     * @return true if successful, false otherwise
     */
    public boolean confirmAndPersist(String bannerId, String newImageUrl) {
        Banner banner = getBannerDetails(bannerId);
        if (banner == null) {
            errorHandler.handleError("BANNER_NOT_FOUND", "Banner not found: " + bannerId);
            return false;
        }
        
        banner.updateImage(newImageUrl);
        persistUpdate(banner);
        
        // Send success notification to the operator (assuming we have operator ID from context)
        String operatorId = "currentOperator"; // In a real app, this would come from session/context
        notificationService.sendSuccessNotification(operatorId, "Banner modified successfully");
        
        return true;
    }
    
    /**
     * Persists the updated banner to the repository.
     * @param banner the updated banner
     */
    private void persistUpdate(Banner banner) {
        try {
            bannerRepository.save(banner);
        } catch (Exception e) {
            errorHandler.handleError("PERSISTENCE_ERROR", "Failed to save banner: " + e.getMessage());
        }
    }
    
    /**
     * Simulates operator authentication.
     * @param operatorId the operator ID
     * @return an AgencyOperator object if authenticated, null otherwise
     */
    private AgencyOperator authenticateOperator(String operatorId) {
        // Simulate authentication: check if operator exists in a system
        // For demonstration, assume valid operator IDs start with "op"
        if (operatorId == null || !operatorId.startsWith("op")) {
            return null;
        }
        // In a real system, this would query a user database
        return new AgencyOperator(operatorId, "Operator " + operatorId, operatorId + "@example.com");
    }
}