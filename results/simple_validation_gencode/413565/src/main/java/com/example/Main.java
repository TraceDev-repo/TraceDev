package com.example;

import com.example.adapter.in.web.CulturalGoodController;
import com.example.adapter.out.persistence.JpaCulturalGoodRepository;
import com.example.application.service.EditCulturalGoodService;
import com.example.domain.CulturalGood;
import com.example.infrastructure.ServerConnection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Main class to demonstrate the system.
 */
public class Main {
    public static void main(String[] args) {
        // Setup infrastructure
        ServerConnection serverConnection = new ServerConnection();
        serverConnection.monitorConnection();

        // Setup repository (simulated, no actual JPA here)
        JpaCulturalGoodRepository repository = new JpaCulturalGoodRepository();

        // Setup service
        EditCulturalGoodService service = new EditCulturalGoodService(repository, serverConnection);

        // Setup controller
        CulturalGoodController controller = new CulturalGoodController(service);

        // Simulate a cultural good
        CulturalGood good = new CulturalGood("1", "Ancient Vase", "A fine ancient vase", "Artifact", "Room 101", new Date());
        // In a real scenario, the repository would save it.

        // Simulate sequence diagram flow
        System.out.println("1. Display list:");
        controller.displayList().forEach(g -> System.out.println(g.getName()));

        System.out.println("\n2. Select cultural good:");
        CulturalGood selected = controller.selectCulturalGood("1");
        System.out.println("Selected: " + (selected != null ? selected.getName() : "null"));

        System.out.println("\n3. Activate change:");
        controller.activateChange("1");

        System.out.println("\n4. Show edit form:");
        var form = controller.showEditForm(good);
        System.out.println("Form created for: " + form.getName());

        System.out.println("\n5. Submit edit form:");
        Map<String, Object> formData = new HashMap<>();
        formData.put("name", "Ancient Vase Renamed");
        formData.put("description", "Updated description");
        String result = controller.submitEditForm("1", formData);
        System.out.println("Result: " + result);

        System.out.println("\nSimulation completed.");
    }
}