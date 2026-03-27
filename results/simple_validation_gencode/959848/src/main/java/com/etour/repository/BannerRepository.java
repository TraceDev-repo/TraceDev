package com.etour.repository;

import com.etour.domain.Banner;
import java.util.List;

/**
 * Interface for Banner repository operations.
 */
public interface BannerRepository {
    List<Banner> findBannersByTurningPointId(String turningPointId);
    Banner findBannerById(String bannerId);
    void save(Banner banner);
}