package com.etour.view;

import com.etour.controller.EditRefreshmentPointController;
import com.etour.dto.RefreshmentPointDTO;
import java.util.List;

/**
 * Displays a list of refreshment points and allows selection.
 * Notifies the controller when a point is selected.
 */
public class RefreshmentPointListView {
    private EditRefreshmentPointController controller;
    private List<RefreshmentPointDTO> points;

    public RefreshmentPointListView(EditRefreshmentPointController controller) {
        this.controller = controller;
    }

    /**
     * Displays the list of refreshment points.
     * @param points The list of points to display.
     */
    public void displayPoints(List<RefreshmentPointDTO> points) {
        this.points = points;
        System.out.println("Displaying " + points.size() + " refreshment points.");
        for (RefreshmentPointDTO point : points) {
            System.out.println("  ID: " + point.id + ", Name: " + point.name);
        }
    }

    /**
     * Called when a point is selected by the user.
     * @param pointId The ID of the selected point.
     */
    public void onPointSelected(String pointId) {
        System.out.println("Point selected: " + pointId);
        controller.handleRefreshmentPointSelection(pointId);
    }

    /**
     * Refreshes the list of points (e.g., after an update).
     */
    public void refreshList() {
        System.out.println("Refreshing point list...");
        // In a real implementation, this would fetch the latest data from the service.
    }
}