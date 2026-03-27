package com.etour.repository;

import com.etour.domain.Banner;
import com.etour.infrastructure.NetworkConnectionManager;
import com.etour.exceptions.ServerConnectionException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementation of BannerRepository that uses an in‑memory data source.
 * Uses NetworkConnectionManager to check connectivity.
 */
public class BannerRepositoryImpl implements BannerRepository {
    // Simulating a data source with an in‑memory map
    private Map<String, Banner> bannerStore = new HashMap<>();
    private NetworkConnectionManager networkConnectionManager;
    
    public BannerRepositoryImpl(NetworkConnectionManager networkConnectionManager) {
        this.networkConnectionManager = networkConnectionManager;
        // Initialize with some dummy data for demonstration
        bannerStore.put("banner1", new Banner("banner1", "Banner 1", "http://example.com/old1.jpg", "point1"));
        bannerStore.put("banner2", new Banner("banner2", "Banner 2", "http://example.com/old2.jpg", "point1"));
        bannerStore.put("banner3", new Banner("banner3", "Banner 3", "http://example.com/old3.jpg", "point2"));
    }
    
    @Override
    public List<Banner> findBannersByTurningPointId(String turningPointId) {
        // Simulate potential server connection exception
        if (!networkConnectionManager.checkConnection()) {
            throw new ServerConnectionException("DATABASE_CONNECTION_FAILED");
        }
        
        List<Banner> result = new ArrayList<>();
        for (Banner banner : bannerStore.values()) {
            if (banner.getTurningPointId().equals(turningPointId)) {
                result.add(banner);
            }
        }
        return result;
    }
    
    @Override
    public Banner findBannerById(String bannerId) {
        // Simulate potential server connection exception
        if (!networkConnectionManager.checkConnection()) {
            throw new ServerConnectionException("DATABASE_CONNECTION_FAILED");
        }
        
        return bannerStore.get(bannerId);
    }
    
    @Override
    public void save(Banner banner) {
        // Simulate potential server connection exception
        if (!networkConnectionManager.checkConnection()) {
            throw new ServerConnectionException("DATABASE_CONNECTION_FAILED");
        }
        
        bannerStore.put(banner.getId(), banner);
    }
}