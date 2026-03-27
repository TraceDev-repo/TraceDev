package com.example.infrastructure;

import com.example.domain.Banner;

/**
 * Repository interface for Banner persistence operations.
 */
public interface BannerRepository {
    Banner save(Banner banner);
    int countByRefreshmentPointId(String refreshmentPointId);
}