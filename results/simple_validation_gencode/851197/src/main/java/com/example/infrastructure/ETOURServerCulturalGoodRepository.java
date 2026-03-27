package com.example.infrastructure;

import com.example.domain.CulturalGood;
import com.example.domain.CulturalGoodType;
import com.example.repository.CulturalGoodRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Repository implementation that uses the ETOUR Server.
 */
public class ETOURServerCulturalGoodRepository implements CulturalGoodRepository {
    private ETOURServerClient serverClient;

    public ETOURServerCulturalGoodRepository(ETOURServerClient serverClient) {
        this.serverClient = serverClient;
    }

    @Override
    public Optional<CulturalGood> findById(int id) {
        // Check connection
        if (!serverClient.isConnected()) {
            System.err.println("Connection to ETOUR server interrupted.");
            return Optional.empty();
        }

        // Fetch data from server
        String data = serverClient.fetchCulturalGoodData(id);
        // Parse the data (simplified parsing for demonstration)
        // In real implementation, use JSON/XML parser
        CulturalGood good = parseCulturalGood(data);
        return Optional.ofNullable(good);
    }

    @Override
    public List<CulturalGood> findAll() {
        // Added to satisfy requirement REQ-SEQ-001
        if (!serverClient.isConnected()) {
            System.err.println("Connection to ETOUR server interrupted.");
            return new ArrayList<>();
        }

        String data = serverClient.fetchAllCulturalGoodsData();
        // Parse the array data (simplified)
        List<CulturalGood> goods = parseCulturalGoodsList(data);
        return goods;
    }

    private CulturalGood parseCulturalGood(String data) {
        // Simplified parsing - in reality, use a proper JSON library
        // Assuming data format: {"id":1, "name":"...", "description":"...", "location":"...", "type":"MONUMENT"}
        try {
            // Extract values (dummy extraction for simulation)
            int id = 1; // extracted from JSON
            String name = "Sample Cultural Good";
            String description = "A description";
            String location = "Rome";
            CulturalGoodType type = CulturalGoodType.MONUMENT;
            return new CulturalGood(id, name, description, location, type);
        } catch (Exception e) {
            System.err.println("Failed to parse cultural good data: " + e.getMessage());
            return null;
        }
    }

    private List<CulturalGood> parseCulturalGoodsList(String data) {
        // Simplified parsing for list
        List<CulturalGood> goods = new ArrayList<>();
        // Simulate two items
        goods.add(new CulturalGood(1, "Colosseum", "Ancient amphitheater", "Rome", CulturalGoodType.MONUMENT));
        goods.add(new CulturalGood(2, "David", "Renaissance sculpture", "Florence", CulturalGoodType.ARTIFACT));
        return goods;
    }
}