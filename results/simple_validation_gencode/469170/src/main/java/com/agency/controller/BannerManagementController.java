package com.agency.controller;

import com.agency.dto.DeleteBannerResult;
import com.agency.dto.SearchCriteria;
import com.agency.entity.AgencyOperator;
import com.agency.entity.Banner;
import com.agency.entity.RefreshmentPoint;
import com.agency.service.BannerService;
import java.util.List;

/**
 * Controller handling banner management UI interactions.
 */
public class BannerManagementController {
    private BannerService bannerService;
    private AgencyOperator currentOperator;
    private boolean authenticated;
    private boolean authorized;

    public BannerManagementController(BannerService bannerService) {
        this.bannerService = bannerService;
    }

    public List<RefreshmentPoint> getRefreshmentPoints(SearchCriteria searchCriteria) {
        // Delegates to service layer
        return bannerService.getRefreshmentPoints(searchCriteria);
    }

    public RefreshmentPoint selectRefreshmentPoint(String refreshmentPointId) {
        // In a real implementation, this would retrieve a single refreshment point
        // For simplicity, we return a dummy point
        return new RefreshmentPoint(refreshmentPointId, "Selected Point", "Unknown Location");
    }

    public List<Banner> getBannersForRefreshmentPoint(String refreshmentPointId) {
        return bannerService.getBannersForRefreshmentPoint(refreshmentPointId);
    }

    public boolean accessBannerRemoval() {
        // Explicit access step: returns true if removal is allowed
        // In a real system, this might check additional business rules
        return true;
    }

    public boolean confirmDeletion(String bannerId) {
        // Simulate user confirmation; in real UI, this would wait for user input
        return true;
    }

    public boolean deleteBanner(String bannerId) {
        // Deletes a banner by its ID.
        // Current operator ID and permission are known from the controller state
        String operatorId = currentOperator != null ? currentOperator.getId() : null;
        String permission = "MANAGE_BANNERS";
        DeleteBannerResult result = bannerService.deleteBanner(operatorId, bannerId, permission);
        return result.isSuccess();
    }

    public AgencyOperator getCurrentOperator() {
        return currentOperator;
    }

    public void setCurrentOperator(AgencyOperator currentOperator) {
        this.currentOperator = currentOperator;
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }

    public boolean isAuthorized() {
        return authorized;
    }

    public void setAuthorized(boolean authorized) {
        this.authorized = authorized;
    }
}