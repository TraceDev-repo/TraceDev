package com.agency.reporting.infrastructure.persistence;

import com.agency.reporting.domain.Location;
import java.util.List;

/**
 * Repository port for locations.
 */
public interface LocationRepository {
    List<Location> findAll();
    List<Location> findByAgency(String agencyId);
}