package com.example.presentation;

import com.example.dto.CulturalGoodDTO;
import java.util.List;

/**
 * Boundary class for displaying a list of cultural goods.
 */
public class CulturalGoodsListView {
    
    public void displayCulturalGoods(List<CulturalGoodDTO> goodsList) {
        System.out.println("=== Cultural Goods List ===");
        for (CulturalGoodDTO good : goodsList) {
            System.out.println(good.getId() + ": " + good.getName() + " [" + good.getType() + "]");
        }
        System.out.println("===========================");
    }

    public void showError(String message) {
        System.err.println("Error in List View: " + message);
    }

    /**
     * Updates the list of cultural goods (called from controller).
     * Added to satisfy requirement REQ-SEQ-001.
     * @param goodsList the new list
     */
    public void updateCulturalGoodsList(List<CulturalGoodDTO> goodsList) {
        System.out.println("List view updated with " + goodsList.size() + " items.");
        displayCulturalGoods(goodsList);
    }

    // This method simulates the actor selecting a cultural good from the UI.
    // In a real UI, this would be triggered by a user action.
    public void selectCulturalGood(int selectedGoodId) {
        System.out.println("User selected cultural good with ID: " + selectedGoodId);
        // Normally this would call the controller, but for simplicity,
        // we assume the controller is already listening to UI events.
    }
}