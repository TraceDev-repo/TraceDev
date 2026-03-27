package com.example.infrastructure;

import com.example.domain.Banner;

import java.util.List;

/**
 * Repository interface for Banner entities.
 */
public interface BannerRepository {
    List<Banner> findByRefreshmentPointId(String refreshmentPointId);
    Banner save(Banner banner);
}