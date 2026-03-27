package com.example.repository;

import com.example.refreshmentpoint.RefreshmentPoint;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * In-memory implementation of RefreshmentPointRepository for demonstration.
 * In a real system, this would connect to a database.
 */
public class RefreshmentPointRepositoryImpl implements RefreshmentPointRepository {
    // Simulating a simple data source as a list
    private List<RefreshmentPoint> dataSource;

    public RefreshmentPointRepositoryImpl() {
        this.dataSource = new ArrayList<>();
        // Add some sample data for testing
        dataSource.add(new RefreshmentPoint("RP1", "Cafe Central", "Main Square"));
        dataSource.add(new RefreshmentPoint("RP2", "Water Fountain", "Park Entrance"));
    }

    @Override
    public Optional<RefreshmentPoint> findById(String id) {
        return dataSource.stream()
                .filter(point -> point.getPointId().equals(id))
                .findFirst();
    }

    @Override
    public void deleteById(String id) {
        dataSource.removeIf(point -> point.getPointId().equals(id));
        System.out.println("Repository: Deleted point with ID " + id);
    }

    @Override
    public List<RefreshmentPoint> findAll() {
        return new ArrayList<>(dataSource);
    }
}