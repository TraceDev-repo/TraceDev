package com.example.infrastructure.repository;

import com.example.domain.CulturalHeritage;
import com.example.domain.repository.ICulturalHeritageRepository;
import java.util.ArrayList;
import java.util.List;

/**
 * Mock implementation of ICulturalHeritageRepository for demonstration.
 * In a real application, this would connect to a database.
 */
public class CulturalHeritageRepositoryImpl implements ICulturalHeritageRepository {
    // Mock data storage
    private List<CulturalHeritage> culturalHeritages;

    public CulturalHeritageRepositoryImpl() {
        culturalHeritages = new ArrayList<>();
        // Add some mock data
        culturalHeritages.add(new CulturalHeritage("1", "Eiffel Tower", "Iconic tower in Paris"));
        culturalHeritages.add(new CulturalHeritage("2", "Colosseum", "Ancient amphitheater in Rome"));
        culturalHeritages.add(new CulturalHeritage("3", "Taj Mahal", "Mausoleum in India"));
    }

    @Override
    public List<CulturalHeritage> findAll() {
        return new ArrayList<>(culturalHeritages);
    }

    @Override
    public CulturalHeritage findById(String id) {
        return culturalHeritages.stream()
                .filter(ch -> ch.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void delete(String id) {
        culturalHeritages.removeIf(ch -> ch.getId().equals(id));
        System.out.println("[REPOSITORY] Cultural heritage with ID " + id + " deleted.");
    }
}