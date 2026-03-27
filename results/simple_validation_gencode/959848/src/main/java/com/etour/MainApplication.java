package com.etour;

import com.etour.agency.AgencyOperator;
import com.etour.domain.ImageValidator;
import com.etour.infrastructure.*;
import com.etour.repository.BannerRepository;
import com.etour.repository.BannerRepositoryImpl;
import com.etour.service.ChangeBannerImageService;
import com.etour.service.SearchRefreshmentPointService;
import com.etour.web.ChangeBannerImageController;
import com.etour.web.BannerDTO;
import java.util.List;
import java.util.Map;

/**
 * Main application class that wires up all components and simulates the use-case flow.
 */
public class MainApplication {
    public static void main(String[] args) {
        System.out.println("=== eTour System - Change Banner Image Use Case ===\n");
        
        // 1. Create infrastructure components
        NetworkConnectionManager networkManager = new NetworkConnectionManager();
        NotificationService notificationService = new NotificationService();
        
        // Create ErroredUseCaseHandler first with a temporary null ErrorHandler
        ErroredUseCaseHandler erroredHandler = new ErroredUseCaseHandler(null);
        // Now create ErrorHandler with the required dependencies
        ErrorHandler errorHandler = new ErrorHandlerImpl(notificationService, erroredHandler);
        // Update ErroredUseCaseHandler with the actual ErrorHandler
        erroredHandler = new ErroredUseCaseHandler(errorHandler);
        // Re-create ErrorHandler with the updated ErroredUseCaseHandler to avoid circular dependency
        errorHandler = new ErrorHandlerImpl(notificationService, erroredHandler);
        
        // 2. Create domain validator
        ImageValidator imageValidator = new ImageValidator();
        
        // 3. Create repository
        BannerRepository bannerRepository = new BannerRepositoryImpl(networkManager);
        
        // 4. Create services
        SearchRefreshmentPointService searchService = new SearchRefreshmentPointService();
        ChangeBannerImageService changeService = new ChangeBannerImageService(
            bannerRepository, imageValidator, errorHandler, notificationService, searchService);
        
        // 5. Create controller
        ChangeBannerImageController controller = new ChangeBannerImageController(
            changeService, errorHandler, networkManager);
        
        // Simulate an Agency Operator logging in
        String operatorId = "op123";
        System.out.println("Operator " + operatorId + " logs in.\n");
        
        // Flow of Events 1: Integration with SearchRefreshmentPoint use case
        System.out.println("1. Operator invokes search for turning points...");
        List<Map<String, String>> turningPoints = controller.displayTurningPoints(operatorId);
        System.out.println("   Turning points found: " + turningPoints.size());
        for (Map<String, String> tp : turningPoints) {
            System.out.println("     - " + tp.get("name") + " (ID: " + tp.get("id") + ")");
        }
        System.out.println();
        
        // Assume operator selects first turning point
        String selectedTurningPointId = "point1";
        System.out.println("2. Operator selects turning point: " + selectedTurningPointId);
        
        // Display banners for selected turning point
        System.out.println("3. Loading banners for selected point...");
        List<BannerDTO> banners = controller.displayBanners(selectedTurningPointId);
        System.out.println("   Banners found: " + banners.size());
        for (BannerDTO dto : banners) {
            System.out.println("     - " + dto.getName() + " (ID: " + dto.getId() + ")");
        }
        System.out.println();
        
        // Assume operator selects first banner
        String selectedBannerId = "banner1";
        System.out.println("4. Operator selects banner: " + selectedBannerId);
        
        // Show image selection form (Flow of Events 7 & 8) - explicit form display
        System.out.println("5. Show image selection form...");
        BannerDTO bannerDetails = controller.showImageSelectionForm(selectedBannerId);
        if (bannerDetails != null) {
            System.out.println("   Banner: " + bannerDetails.getName());
            System.out.println("   Current image: " + bannerDetails.getCurrentImageUrl());
            System.out.println("   Form displayed to user for image input.");
        }
        System.out.println();
        
        // Operator submits a new image (Flow of Events 9 & 10) - separate validation step
        String newImageUrl = "http://example.com/new_banner.jpg";
        System.out.println("6. Operator submits new image URL for validation: " + newImageUrl);
        Map<String, Object> validationResult = controller.submitImageSelection(selectedBannerId, newImageUrl);
        System.out.println("   Validation result: " + validationResult.get("message"));
        System.out.println();
        
        // If validation passes, operator confirms change request (Flow of Events 11 & 12) - separate submission
        if ((Boolean) validationResult.get("validationResult")) {
            System.out.println("7. User confirmation prompt displayed.");
            System.out.println("8. Operator confirms change request...");
            Map<String, Object> changeResult = controller.submitChangeRequest(selectedBannerId, newImageUrl);
            System.out.println("   Change result: " + changeResult.get("message"));
        } else {
            System.out.println("7. Image invalid, change request not submitted.");
        }
        
        System.out.println("\n=== Use case simulation complete. ===");
    }
}