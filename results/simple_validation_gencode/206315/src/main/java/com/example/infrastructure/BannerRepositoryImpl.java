package com.example.infrastructure;

import com.example.domain.Banner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Simple in-memory implementation of BannerRepository.
 */
public class BannerRepositoryImpl implements BannerRepository {
    private Map<String, List<Banner>> storage = new HashMap<>();

    public BannerRepositoryImpl() {
        // Initialize with some dummy data
        List<Banner> banners = new ArrayList<>();
        banners.add(new Banner("b1", "Sponsor Banner", "conv1", "rp1"));
        banners.add(new Banner("b2", "Welcome Banner", "conv1", "rp1"));
        storage.put("rp1", banners);
    }

    @Override
    public List<Banner> findByRefreshmentPointId(String refreshmentPointId) {
        return storage.getOrDefault(refreshmentPointId, new ArrayList<>());
    }

    @Override
    public Banner save(Banner banner) {
        String rpId = banner.getRefreshmentPointId();
        storage.computeIfAbsent(rpId, k -> new ArrayList<>()).add(banner);
        return banner;
    }
}