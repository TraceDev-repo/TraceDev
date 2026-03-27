package com.example;

import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of the cultural object repository.
 * Simulates database operations.
 */
public class CulturalObjectRepositoryImpl implements CulturalObjectRepository {
    // Simulating an in-memory store for demonstration.
    private Map<Long, CulturalObject> store = new HashMap<>();
    private Map<String, Long> uniqueIndex = new HashMap<>(); // key: "name|type|location"
    private long nextId = 1;

    @Override
    public CulturalObject save(CulturalObject culturalObject) {
        if (culturalObject.getId() == null) {
            // New entity: assign ID
            culturalObject.setId(nextId++);
        } else {
            // Update: remove old unique index entry if name/type/location changed.
            // For simplicity, we assume no updates in this flow.
        }
        // Update unique index
        String uniqueKey = culturalObject.getName() + "|" + culturalObject.getType() + "|" + culturalObject.getLocation();
        uniqueIndex.put(uniqueKey, culturalObject.getId());

        // Store entity
        store.put(culturalObject.getId(), culturalObject);
        return culturalObject;
    }

    @Override
    public boolean existsByUniqueProperties(Map<String, Object> uniqueProperties) {
        String name = (String) uniqueProperties.get("name");
        String type = (String) uniqueProperties.get("type");
        String location = (String) uniqueProperties.get("location");
        String uniqueKey = name + "|" + type + "|" + location;
        return uniqueIndex.containsKey(uniqueKey);
    }
}