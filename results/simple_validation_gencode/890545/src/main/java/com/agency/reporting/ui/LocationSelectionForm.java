package com.agency.reporting.ui;

import com.agency.reporting.domain.Location;
import com.agency.reporting.ui.viewmodel.LocationViewModel;
import com.agency.reporting.ui.viewmodel.ReportViewModel;
import java.util.List;

/**
 * UI component for location selection.
 */
public class LocationSelectionForm {
    private LocationViewModel viewModel;
    private ReportViewModel reportViewModel;
    private String selectedLocationId;

    public LocationSelectionForm(LocationViewModel viewModel, ReportViewModel reportViewModel) {
        this.viewModel = viewModel;
        this.reportViewModel = reportViewModel;
    }

    public void displayLocations(List<Location> locations) {
        System.out.println("Displaying locations:");
        for (Location loc : locations) {
            System.out.println(" - " + loc.getId() + ": " + loc.getName());
        }
    }

    public void setSelectedLocation(String locationId) {
        this.selectedLocationId = locationId;
        viewModel.setSelectedLocationId(locationId);
    }

    public void submitForm() {
        if (selectedLocationId != null) {
            System.out.println("Form submitted for location: " + selectedLocationId);
            // Trigger report generation via ReportViewModel
            reportViewModel.generateReport(selectedLocationId);
        } else {
            System.out.println("No location selected");
        }
    }

    public String getSelectedLocationId() {
        return selectedLocationId;
    }

    // For integration with UI flow
    public void displayForm() {
        // Load locations via view model
        viewModel.loadLocations();
        List<Location> locations = viewModel.getLocations();
        if (locations != null && !locations.isEmpty()) {
            displayLocations(locations);
        }
    }
}