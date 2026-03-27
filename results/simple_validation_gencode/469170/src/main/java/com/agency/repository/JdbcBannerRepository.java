package com.agency.repository;

import com.agency.entity.Banner;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * JDBC implementation of BannerRepository.
 */
public class JdbcBannerRepository implements BannerRepository {
    @Override
    public List<Banner> findByRefreshmentPointId(String refreshmentPointId) {
        // Simulating retrieval by refreshment point ID
        List<Banner> banners = new ArrayList<>();
        if ("rp1".equals(refreshmentPointId)) {
            banners.add(new Banner("b1", "rp1", "http://example.com/banner1.jpg",
                    new Date(System.currentTimeMillis() - 86400000),
                    new Date(System.currentTimeMillis() + 86400000)));
            banners.add(new Banner("b2", "rp1", "http://example.com/banner2.jpg",
                    new Date(System.currentTimeMillis() - 172800000),
                    new Date(System.currentTimeMillis() + 172800000)));
        }
        return banners;
    }

    @Override
    public Optional<Banner> findById(String id) {
        // Simulating retrieval by banner ID
        if ("b1".equals(id)) {
            return Optional.of(new Banner("b1", "rp1", "http://example.com/banner1.jpg",
                    new Date(System.currentTimeMillis() - 86400000),
                    new Date(System.currentTimeMillis() + 86400000)));
        } else if ("b2".equals(id)) {
            return Optional.of(new Banner("b2", "rp1", "http://example.com/banner2.jpg",
                    new Date(System.currentTimeMillis() - 172800000),
                    new Date(System.currentTimeMillis() + 172800000)));
        }
        return Optional.empty();
    }

    @Override
    public boolean delete(String bannerId) {
        // Simulating deletion; assume always successful for demonstration
        return true;
    }
}