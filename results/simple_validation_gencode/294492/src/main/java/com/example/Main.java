package com.example;

import com.example.domain.Banner;
import com.example.domain.RefreshmentPoint;
import com.example.infrastructure.BannerRepository;
import com.example.infrastructure.ImageValidator;
import com.example.infrastructure.RefreshmentPointRepository;
import com.example.service.*;
import com.example.ui.SelectionPage;
import com.example.web.controller.InsertBannerController;
import com.example.web.dto.BannerDto;
import com.example.web.dto.RefreshmentPointDto;
import com.example.web.form.BannerInsertionForm;
import com.example.web.result.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Main class to simulate the flow described in the sequence diagram.
 * This is a runnable demonstration that uses dummy repository implementations.
 */
public class Main {
    public static void main(String[] args) {
        // Setup infrastructure (dummy implementations for demonstration).
        RefreshmentPointRepository dummyPointRepo = new DummyRefreshmentPointRepository();
        BannerRepository dummyBannerRepo = new DummyBannerRepository();
        ImageValidator imageValidator = new ImageValidator();

        // Setup services.
        RefreshmentPointService refreshmentPointService = new RefreshmentPointServiceImpl(dummyPointRepo);
        BannerService bannerService = new BannerServiceImpl(dummyBannerRepo, dummyPointRepo, imageValidator);
        SearchRefreshmentPointService searchService = new DummySearchService(dummyPointRepo, dummyBannerRepo);

        // Setup controller.
        InsertBannerController controller = new InsertBannerController(bannerService, refreshmentPointService, searchService);

        // Step 1: Get refreshment points (sequence diagram steps 1‑10).
        System.out.println("=== Step 1: Get refreshment points ===");
        List<RefreshmentPointDto> points = controller.getRefreshmentPoints();
        SelectionPage selectionPage = new SelectionPage();
        selectionPage.displayRefreshmentPoints(points);

        // Step 2 & 3: Select point and initiate insertion.
        System.out.println("\n=== Step 2 & 3: Initiate banner insertion ===");
        String selectedId = selectionPage.getSelectedPointId();
        BannerInsertionForm form = controller.initiateBannerInsertion(selectedId);
        System.out.println("Created form for point: " + form.getRefreshmentPointName());

        // Step 5‑6: Simulate user selecting an image and submitting.
        System.out.println("\n=== Step 5‑6: Upload and validate banner ===");
        byte[] dummyImage = new byte[1024]; // 1KB dummy image
        BannerDto bannerDto = new BannerDto(selectedId, dummyImage, "jpg");
        ValidationResult validationResult = controller.uploadAndValidateBanner(bannerDto);

        if (validationResult.isValid()) {
            System.out.println("Validation passed. Confirmation requested: " + validationResult.getConfirmationRequest().getMessage());

            // Step 10: Simulate user confirming.
            System.out.println("\n=== Step 10: Confirm insertion ===");
            OperationResult saveResult = controller.confirmInsertion(bannerDto);
            System.out.println("Save result: " + saveResult.getMessage());
        } else {
            System.out.println("Validation failed: " + validationResult.getMessage());
        }

        // Alternative flow: cancellation.
        System.out.println("\n=== Alternative flow: Cancel insertion ===");
        CancellationResult cancelResult = controller.cancelInsertion(bannerDto);
        System.out.println("Cancellation result: cancelled=" + cancelResult.isCancelled() + ", cleanup=" + cancelResult.isCleanupPerformed());
    }

    // Dummy implementations for demonstration.

    static class DummyRefreshmentPointRepository implements RefreshmentPointRepository {
        @Override
        public List<RefreshmentPoint> findAll() {
            RefreshmentPoint p = new RefreshmentPoint("point1", "Cafeteria", 5);
            p.setBanners(Arrays.asList(new Banner(), new Banner())); // two existing banners
            return Arrays.asList(p);
        }

        @Override
        public Optional<RefreshmentPoint> findById(String id) {
            RefreshmentPoint p = new RefreshmentPoint(id, "Cafeteria", 5);
            p.setBanners(Arrays.asList(new Banner(), new Banner()));
            return Optional.of(p);
        }
    }

    static class DummyBannerRepository implements BannerRepository {
        @Override
        public Banner save(Banner banner) {
            System.out.println("DummyBannerRepository: saved banner " + banner.getId());
            return banner;
        }

        @Override
        public int countByRefreshmentPointId(String refreshmentPointId) {
            // For demonstration, always return 2 (so point with max=5 can accept a new banner).
            return 2;
        }
    }

    static class DummySearchService implements SearchRefreshmentPointService {
        private RefreshmentPointRepository pointRepo;
        private BannerRepository bannerRepo;

        DummySearchService(RefreshmentPointRepository pointRepo, BannerRepository bannerRepo) {
            this.pointRepo = pointRepo;
            this.bannerRepo = bannerRepo;
        }

        @Override
        public List<RefreshmentPointDto> getAllRefreshmentPoints() {
            List<RefreshmentPoint> points = pointRepo.findAll();
            // Convert each point to DTO with current banner count.
            return points.stream()
                    .map(p -> new RefreshmentPointDto(p.getId(), p.getName(),
                            bannerRepo.countByRefreshmentPointId(p.getId()),
                            p.getMaxBanners()))
                    .toList();
        }
    }
}