package com.example.presentation;

import com.example.application.ViewCulturalGoodUseCaseController;
import com.example.dto.CulturalGoodDTO;
import com.example.dto.CulturalGoodDetailDTO;
import java.util.List;

/**
 * Controller mediating between views and use case controller.
 */
public class CulturalGoodController {
    private CulturalGoodsListView viewList;
    private CulturalGoodDetailView viewDetail;
    private ViewCulturalGoodUseCaseController useCaseController;

    public CulturalGoodController(CulturalGoodsListView viewList, CulturalGoodDetailView viewDetail, ViewCulturalGoodUseCaseController useCaseController) {
        this.viewList = viewList;
        this.viewDetail = viewDetail;
        this.useCaseController = useCaseController;
    }

    /**
     * Handles the selection of a cultural good from the list.
     * @param selectedGoodId the selected good ID
     */
    public void handleCulturalGoodSelection(int selectedGoodId) {
        // Show loading in detail view
        viewDetail.showLoading();

        // Execute use case to get details
        CulturalGoodDetailDTO details = useCaseController.executeViewDetail(selectedGoodId);

        // Handle result
        if (details != null) {
            viewDetail.displayCulturalGoodDetails(details);
        } else {
            viewDetail.showError("Could not load details.");
        }
        viewDetail.hideLoading();
    }

    /**
     * Handles updating the list of cultural goods (called from previous use case).
     * Added to satisfy requirement REQ-SEQ-001.
     * @param goodsList the list of CulturalGoodDTO objects
     */
    public void handleUpdateCulturalGoodsList(List<CulturalGoodDTO> goodsList) {
        // According to the design, we should call the useCaseController.updateCulturalGoodsList
        // with the list, and then pass the list to the view.
        useCaseController.updateCulturalGoodsList(goodsList);
        viewList.updateCulturalGoodsList(goodsList);
    }
}