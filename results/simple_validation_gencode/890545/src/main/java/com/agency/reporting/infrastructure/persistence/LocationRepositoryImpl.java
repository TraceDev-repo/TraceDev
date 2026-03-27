package com.agency.reporting.infrastructure.persistence;

import com.agency.reporting.domain.Location;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of LocationRepository.
 */
public class LocationRepositoryImpl implements LocationRepository {
    @Override
    public List<Location> findAll() {
        // Simulate fetching all locations
        return Arrays.asList(
                new Location("loc1", "Location One"),
                new Location("loc2", "Location Two"),
                new Location("loc3", "Location Three")
        );
    }

    @Override
    public List<Location> findByAgency(String agencyId) {
        // Simulate fetching agency-specific locations
        // In real implementation, would filter by agencyId
        System.out.println("Fetching locations for agency: " + agencyId);
        // Return filtered locations based on agencyId
        return findAll().stream()
                .filter(location -> location.getId().equals("loc1") && "agency1".equals(agencyId))
                .collect(Collectors.toList());
    }
}