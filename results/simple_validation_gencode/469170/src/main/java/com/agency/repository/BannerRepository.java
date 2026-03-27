package com.agency.repository;

import com.agency.entity.Banner;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for banner data access.
 */
public interface BannerRepository {
    List<Banner> findByRefreshmentPointId(String refreshmentPointId);
    Optional<Banner> findById(String id);
    boolean delete(String bannerId);
}