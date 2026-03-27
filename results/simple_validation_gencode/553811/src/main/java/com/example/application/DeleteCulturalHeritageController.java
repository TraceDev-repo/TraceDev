package com.example.application;

import com.example.domain.CulturalHeritage;
import com.example.domain.dto.CulturalHeritageDTO;
import com.example.domain.repository.ICulturalHeritageRepository;
import com.example.domain.service.INotificationService;
import com.example.presentation.DeleteCulturalHeritageView;
import com.example.usecase.IExecuteCommandUseCase;
import com.example.usecase.SearchCulturalHeritageUseCase;
import com.example.usecase.command.DeleteCulturalHeritageCommand;
import java.util.List;

/**
 * Controller that orchestrates the deletion of cultural heritages.
 * It manages UI blocking, interacts with use cases, and handles notifications.
 */
public class DeleteCulturalHeritageController {
    private String selectedHeritageId;
    private IExecuteCommandUseCase executeCommandUseCase;
    private INotificationService notificationService;
    private SearchCulturalHeritageUseCase searchUseCase;
    private ICulturalHeritageRepository repository;
    private DeleteCulturalHeritageView view;

    public DeleteCulturalHeritageController(IExecuteCommandUseCase executeCommandUseCase,
                                            INotificationService notificationService,
                                            SearchCulturalHeritageUseCase searchUseCase,
                                            ICulturalHeritageRepository repository,
                                            DeleteCulturalHeritageView view) {
        this.executeCommandUseCase = executeCommandUseCase;
        this.notificationService = notificationService;
        this.searchUseCase = searchUseCase;
        this.repository = repository;
        this.view = view;
    }

    /**
     * Handles the event when the view is loaded.
     * Retrieves the list of cultural heritages and displays them.
     */
    public void handleViewLoaded() {
        List<CulturalHeritageDTO> heritages = searchUseCase.execute();
        view.displayList(heritages);
    }

    /**
     * Handles the selection of a cultural heritage by the user.
     * @param heritageId The ID of the selected heritage.
     */
    public void handleHeritageSelected(String heritageId) {
        this.selectedHeritageId = heritageId;
        CulturalHeritage heritage = repository.findById(heritageId);
        if (heritage != null) {
            CulturalHeritageDTO dto = convertToDTO(heritage);
            view.askForConfirmation(dto);
        } else {
            notificationService.notifyError("Cultural heritage not found with ID: " + heritageId);
        }
    }

    /**
     * Handles the confirmation of the deletion by the user.
     */
    public void handleConfirmationConfirmed() {
        blockUI();
        DeleteCulturalHeritageCommand command = new DeleteCulturalHeritageCommand(selectedHeritageId, repository);
        try {
            executeCommandUseCase.execute(command);
            notificationService.notifySuccess("Cultural heritage deleted successfully");
        } catch (Exception e) {
            notificationService.notifyError("Error during deletion: " + e.getMessage());
        } finally {
            unblockUI();
        }
    }

    /**
     * Handles the cancellation of the operation by the user.
     */
    public void handleOperationCancelled() {
        blockUI();
        notificationService.notifyError("Operation cancelled");
        unblockUI();
    }

    /**
     * Blocks the UI (calls corresponding view method).
     */
    public void blockUI() {
        view.blockInputControls();
    }

    /**
     * Unblocks the UI (calls corresponding view method).
     */
    public void unblockUI() {
        view.unblockInputControls();
    }

    /**
     * Converts a CulturalHeritage entity to a CulturalHeritageDTO.
     * @param heritage The CulturalHeritage entity.
     * @return The corresponding CulturalHeritageDTO.
     */
    private CulturalHeritageDTO convertToDTO(CulturalHeritage heritage) {
        return new CulturalHeritageDTO(heritage.getId(), heritage.getName(), heritage.getDescription());
    }
}