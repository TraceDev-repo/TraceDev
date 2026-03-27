package com.etour;

import com.etour.agency.AgencyOperator;
import com.etour.controller.EditRefreshmentPointController;
import com.etour.datasource.IDataSource;
import com.etour.dto.RefreshmentPointDTO;
import com.etour.repository.IRefreshmentPointRepository;
import com.etour.repository.RefreshmentPointRepository;
import com.etour.server.ETourServerConnection;
import com.etour.server.IETOURServer;
import com.etour.service.IRestPointService;
import com.etour.service.RestPointService;
import com.etour.validation.DTOValidator;
import com.etour.validation.IDTOValidator;
import com.etour.view.ChangeForm;
import com.etour.view.RefreshmentPointListView;
import java.util.ArrayList;
import java.util.List;

/**
 * Main class to demonstrate the flow.
 * Simulates the sequence diagram interactions.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Starting ETour Refreshment Point Edit Demo ===");

        // Setup dependencies
        IETOURServer server = new ETourServerConnection("http://etour-server.com");
        server.connect();

        // Mock data source
        IDataSource dataSource = new MockDataSource();
        IRefreshmentPointRepository repository = new RefreshmentPointRepository(dataSource);
        IRestPointService service = new RestPointService(repository, server);
        IDTOValidator validator = new DTOValidator();
        ChangeForm changeForm = new ChangeForm(validator);
        EditRefreshmentPointController controller = new EditRefreshmentPointController(service, validator, changeForm);
        RefreshmentPointListView listView = new RefreshmentPointListView(controller);

        // Agency operator logs in
        AgencyOperator operator = new AgencyOperator("john_doe", "password");
        operator.login();

        // Simulate viewing points (Flow of Events 2)
        List<RefreshmentPointDTO> points = new ArrayList<>();
        RefreshmentPointDTO dto1 = new RefreshmentPointDTO();
        dto1.id = "RP001";
        dto1.name = "Rest Area Alpha";
        dto1.address = "Highway 1, Mile 10";
        points.add(dto1);
        listView.displayPoints(points);

        // Operator selects a point (via list view as intermediary per sequence diagram)
        listView.onPointSelected("RP001");

        // Controller fetches point and displays form
        RefreshmentPointDTO selectedDto = controller.handleRefreshmentPointSelection("RP001");
        controller.displayChangeForm(selectedDto);

        // Operator changes data and submits (simulate via controller)
        selectedDto.address = "Highway 1, Mile 12"; // Change address
        boolean success = controller.processFormSubmission(selectedDto);

        // Test double submission protection
        System.out.println("\nTesting double submission protection...");
        boolean secondAttempt = controller.processFormSubmission(selectedDto);
        System.out.println("Second submission attempt result: " + secondAttempt);

        if (success) {
            System.out.println("=== Operation completed successfully ===");
        } else {
            System.out.println("=== Operation failed ===");
        }

        operator.logout();
        server.disconnect();
    }

    /**
     * Mock data source for demonstration.
     */
    static class MockDataSource implements IDataSource {
        @Override
        public boolean connect() {
            return true;
        }

        @Override
        public java.sql.ResultSet query(String sql) {
            // Return a mock result set
            return null;
        }

        @Override
        public int update(String sql) {
            // Simulate successful update
            return 1;
        }

        @Override
        public void disconnect() {
            // Do nothing
        }
    }
}