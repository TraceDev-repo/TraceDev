package com.example.application;

import com.example.dto.CulturalGoodDTO;
import com.example.dto.CulturalGoodDetailDTO;
import com.example.service.CulturalGoodQueryService;
import java.util.List;

/**
 * Use Case Controller for viewing cultural good details.
 */
public class ViewCulturalGoodUseCaseController {
    private CulturalGoodQueryService service;

    public ViewCulturalGoodUseCaseController(CulturalGoodQueryService service) {
        this.service = service;
    }

    /**
     * Executes the view detail use case.
     * @param goodId the ID of the cultural good
     * @return the detail DTO, or null if not found/error
     */
    public CulturalGoodDetailDTO executeViewDetail(int goodId) {
        return service.findCulturalGoodDetailById(goodId);
    }

    /**
     * Updates the list of cultural goods (called from previous use case).
     * This method is added to satisfy requirement REQ-SEQ-001.
     * It now accepts a parameter as specified in the design.
     * @param goodsList the list of CulturalGoodDTO objects to update
     */
    public void updateCulturalGoodsList(List<CulturalGoodDTO> goodsList) {
        // According to the design, this method should receive the list
        // from the controller. The method signature now matches the diagram.
        // Implementation currently does nothing with the list,
        // but it could be stored or processed as needed.
        System.out.println("UseCaseController received list of " + goodsList.size() + " goods.");
    }
}