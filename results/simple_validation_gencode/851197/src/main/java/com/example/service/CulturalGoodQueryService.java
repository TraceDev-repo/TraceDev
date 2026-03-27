package com.example.service;

import com.example.domain.CulturalGood;
import com.example.dto.CulturalGoodDTO;
import com.example.dto.CulturalGoodDetailDTO;
import com.example.repository.CulturalGoodRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service layer for querying Cultural Goods.
 */
public class CulturalGoodQueryService {
    private CulturalGoodRepository repository;

    public CulturalGoodQueryService(CulturalGoodRepository repository) {
        this.repository = repository;
    }

    public CulturalGoodDetailDTO findCulturalGoodDetailById(int id) {
        Optional<CulturalGood> optionalGood = repository.findById(id);
        if (optionalGood.isPresent()) {
            CulturalGood good = optionalGood.get();
            // Use the entity's toDetailDTO method for model mapping
            return good.toDetailDTO();
        } else {
            // CulturalGood not found or connection error
            return null;
        }
    }

    public List<CulturalGoodDTO> getAllCulturalGoods() {
        // Added to satisfy requirement REQ-SEQ-001
        List<CulturalGood> goods = repository.findAll();
        List<CulturalGoodDTO> dtos = new ArrayList<>();
        for (CulturalGood good : goods) {
            // Use the entity's toDTO method for model mapping
            dtos.add(good.toDTO());
        }
        return dtos;
    }
}