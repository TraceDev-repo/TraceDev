package com.example;

import com.example.application.DeleteCulturalHeritageController;
import com.example.domain.repository.ICulturalHeritageRepository;
import com.example.domain.service.INotificationService;
import com.example.infrastructure.repository.CulturalHeritageRepositoryImpl;
import com.example.infrastructure.service.UINotificationService;
import com.example.presentation.DeleteCulturalHeritageView;
import com.example.usecase.ExecuteCommandUseCase;
import com.example.usecase.IExecuteCommandUseCase;
import com.example.usecase.SearchCulturalHeritageUseCase;

/**
 * Main class to run the application.
 * Sets up the dependencies and starts the view.
 */
public class Main {
    public static void main(String[] args) {
        // Setup infrastructure
        ICulturalHeritageRepository repository = new CulturalHeritageRepositoryImpl();
        INotificationService notificationService = new UINotificationService();
        IExecuteCommandUseCase executeCommandUseCase = new ExecuteCommandUseCase(repository);
        SearchCulturalHeritageUseCase searchUseCase = new SearchCulturalHeritageUseCase(repository);

        // Create view first (needed for controller)
        DeleteCulturalHeritageView view = new DeleteCulturalHeritageView(null);

        // Create controller
        DeleteCulturalHeritageController controller = new DeleteCulturalHeritageController(
                executeCommandUseCase, notificationService, searchUseCase, repository, view);

        // Inject controller into view
        view.setController(controller);

        // Simulate the flow
        System.out.println("=== Starting Cultural Heritage Deletion ===");
        view.simulateViewLoaded();
        view.simulateHeritageSelection();
    }
}