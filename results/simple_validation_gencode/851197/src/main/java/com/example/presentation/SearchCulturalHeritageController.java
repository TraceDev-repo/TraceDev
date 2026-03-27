package com.example.presentation;

import com.example.dto.CulturalGoodDTO;
import com.example.domain.CulturalGoodType;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller for the Search Cultural Heritage use case.
 * This is referenced from previous use case to provide the initial list.
 */
public class SearchCulturalHeritageController {
    private CulturalGoodController culturalGoodController;

    public SearchCulturalHeritageController(CulturalGoodController culturalGoodController) {
        this.culturalGoodController = culturalGoodController;
    }

    /**
     * Simulates executing a search and returning results.
     * @param criteria search criteria (simplified)
     * @return list of CulturalGoodDTO
     */
    public List<CulturalGoodDTO> executeSearch(String criteria) {
        // Simulate search results
        List<CulturalGoodDTO> results = new ArrayList<>();
        results.add(new CulturalGoodDTO(1, "Colosseum", CulturalGoodType.MONUMENT));
        results.add(new CulturalGoodDTO(2, "David", CulturalGoodType.ARTIFACT));
        results.add(new CulturalGoodDTO(3, "Pompeii", CulturalGoodType.HISTORICAL_SITE));
        return results;
    }

    /**
     * Passes search results to the View Cultural Good use case.
     * Added to satisfy requirement REQ-SEQ-001.
     * @param goodsList the list of CulturalGoodDTO objects
     */
    public void passResultsToViewCulturalGood(List<CulturalGoodDTO> goodsList) {
        culturalGoodController.handleUpdateCulturalGoodsList(goodsList);
    }
}