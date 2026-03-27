package com.example.service;

import com.example.domain.RefreshmentPoint;
import com.example.infrastructure.RefreshmentPointRepository;
import java.util.List;

/**
 * Implementation of RefreshmentPointService.
 */
public class RefreshmentPointServiceImpl implements RefreshmentPointService {
    private RefreshmentPointRepository refreshmentPointRepository;

    public RefreshmentPointServiceImpl(RefreshmentPointRepository refreshmentPointRepository) {
        this.refreshmentPointRepository = refreshmentPointRepository;
    }

    @Override
    public List<RefreshmentPoint> getAllRefreshmentPoints() {
        return refreshmentPointRepository.findAll();
    }

    @Override
    public RefreshmentPoint getRefreshmentPointById(String id) {
        return refreshmentPointRepository.findById(id).orElse(null);
    }
}