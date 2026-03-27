package com.agency.reporting.ui.viewmodel;

import com.agency.reporting.auth.AuthenticationService;
import com.agency.reporting.domain.Location;
import com.agency.reporting.infrastructure.persistence.LocationRepository;
import java.util.List;

/**
 * ViewModel for location selection.
 */
public class LocationViewModel {
    private String agencyId;
    private List<Location> locations;
    private String selectedLocationId;
    private final LocationRepository locationRepository;
    private final AuthenticationService authService;

    public LocationViewModel(LocationRepository locationRepository, AuthenticationService authService) {
        this.locationRepository = locationRepository;
        this.authService = authService;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setSelectedLocationId(String locationId) {
        this.selectedLocationId = locationId;
    }

    public String getSelectedLocationId() {
        return selectedLocationId;
    }

    public void loadLocations() {
        // Get agencyId from authentication service
        this.agencyId = authService.getCurrentAgencyId();
        if (agencyId == null) {
            throw new IllegalStateException("No agency ID available - user not authenticated");
        }
        // Load locations for the agency
        this.locations = locationRepository.findByAgency(agencyId);
    }

    // Overload for testing/direct call
    public void loadLocations(String agencyId) {
        this.agencyId = agencyId;
        this.locations = locationRepository.findByAgency(agencyId);
    }
}