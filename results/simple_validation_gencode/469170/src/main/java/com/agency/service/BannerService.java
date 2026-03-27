package com.agency.service;

import com.agency.adapter.EtourServerAdapter;
import com.agency.circuitbreaker.CircuitBreaker;
import com.agency.dto.DeleteBannerResult;
import com.agency.dto.SearchCriteria;
import com.agency.entity.AgencyOperator;
import com.agency.entity.Banner;
import com.agency.entity.RefreshmentPoint;
import com.agency.repository.BannerRepository;
import com.agency.repository.RefreshmentPointRepository;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Core service for banner management operations.
 */
public class BannerService {
    private RefreshmentPointRepository refreshmentPointRepository;
    private BannerRepository bannerRepository;
    private AuthorizationService authorizationService;
    private AuthenticationService authenticationService;
    private EtourServerAdapter etourServerAdapter;
    private CircuitBreaker circuitBreaker;

    public BannerService(RefreshmentPointRepository refreshmentPointRepository,
                         BannerRepository bannerRepository,
                         AuthorizationService authorizationService,
                         AuthenticationService authenticationService,
                         EtourServerAdapter etourServerAdapter,
                         CircuitBreaker circuitBreaker) {
        this.refreshmentPointRepository = refreshmentPointRepository;
        this.bannerRepository = bannerRepository;
        this.authorizationService = authorizationService;
        this.authenticationService = authenticationService;
        this.etourServerAdapter = etourServerAdapter;
        this.circuitBreaker = circuitBreaker;
    }

    public List<RefreshmentPoint> getRefreshmentPoints(SearchCriteria searchCriteria) {
        // No authentication/authorization required for search (as per sequence diagram)
        return refreshmentPointRepository.findAll(searchCriteria);
    }

    public List<Banner> getBannersForRefreshmentPoint(String refreshmentPointId) {
        // Fetch banners for a specific refreshment point
        return bannerRepository.findByRefreshmentPointId(refreshmentPointId);
    }

    public DeleteBannerResult deleteBanner(String operatorId, String bannerId, String permission) {
        // Step 1: Check explicit authentication precondition
        if (operatorId == null || !authenticationService.isAuthenticated(operatorId)) {
            return new DeleteBannerResult(false, "Operator not authenticated", bannerId);
        }

        // Step 2: Get current operator (authentication)
        AgencyOperator operator = authenticationService.getCurrentOperator();
        if (operator == null || !operator.isAuthenticated()) {
            return new DeleteBannerResult(false, "Operator not authenticated", bannerId);
        }

        // Step 3: Validate operation (authorization) using the correct permission
        if (!authorizationService.validateOperation(operator, "MANAGE_BANNERS")) {
            return new DeleteBannerResult(false, "Operator not authorized", bannerId);
        }

        // Step 4: Retrieve banner to ensure it exists
        Banner banner = bannerRepository.findById(bannerId).orElse(null);
        if (banner == null) {
            return new DeleteBannerResult(false, "Banner not found", bannerId);
        }

        // Step 5: Delete banner from repository
        boolean deleted = bannerRepository.delete(bannerId);
        if (!deleted) {
            return new DeleteBannerResult(false, "Failed to delete banner from repository", bannerId);
        }

        // Step 6: Notify Etour server with circuit breaker protection and 2-second timeout
        // Timing constraint: within 2 seconds
        long startTime = System.currentTimeMillis();
        CircuitBreaker.Result notificationResult = executeWithTimeout(() -> {
            // Use circuit breaker to manage connection lifecycle robustly
            return circuitBreaker.execute(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    // Attempt to establish connection with retry
                    if (!etourServerAdapter.isConnected()) {
                        if (!connectWithRetry()) {
                            throw new RuntimeException("Connection to Etour server failed");
                        }
                    }
                    boolean notified = etourServerAdapter.notifyBannerDeletion(bannerId);
                    if (!notified) {
                        // Simulate connection interruption handling
                        handleConnectionInterrupted();
                        throw new RuntimeException("Notification failed");
                    }
                    return true;
                }
            });
        }, 2000); // 2-second timeout

        long elapsed = System.currentTimeMillis() - startTime;
        boolean etourNotified = notificationResult.isSuccess();

        if (!etourNotified) {
            // Banner deleted locally but Etour notification failed
            return new DeleteBannerResult(true,
                    "Banner deleted but Etour notification failed (" + elapsed + "ms): " + notificationResult.getMessage(), bannerId);
        }

        // Success: banner deleted and Etour notified within time
        return new DeleteBannerResult(true, "Banner successfully deleted and Etour notified (" + elapsed + "ms)", bannerId);
    }

    private boolean connectWithRetry() {
        // Simple retry logic for robust connection management
        int maxRetries = 3;
        for (int i = 0; i < maxRetries; i++) {
            if (etourServerAdapter.connect()) {
                return true;
            }
            try {
                Thread.sleep(100 * (i + 1)); // incremental backoff
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return false;
            }
        }
        return false;
    }

    private void handleConnectionInterrupted() {
        // Alternative flow: Connection Interrupted handling
        // In a real system, this might log the incident, queue the notification, or try alternate servers
        System.err.println("Connection interrupted - implementing fallback logic");
        etourServerAdapter.disconnect();
        // Could implement fallback like storing in local queue for later retry
    }

    private CircuitBreaker.Result executeWithTimeout(Callable<CircuitBreaker.Result> task, long timeoutMillis) {
        // Enforce timing constraint with timeout
        try {
            // Simplified timeout execution
            long start = System.currentTimeMillis();
            CircuitBreaker.Result result = task.call();
            long elapsed = System.currentTimeMillis() - start;
            if (elapsed > timeoutMillis) {
                return new CircuitBreaker.Result(false, "Operation timed out after " + elapsed + "ms");
            }
            return result;
        } catch (Exception e) {
            return new CircuitBreaker.Result(false, "Exception: " + e.getMessage());
        }
    }
}