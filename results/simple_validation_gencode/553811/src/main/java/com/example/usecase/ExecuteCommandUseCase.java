package com.example.usecase;

import com.example.domain.repository.ICulturalHeritageRepository;
import com.example.usecase.command.DeleteCulturalHeritageCommand;

/**
 * Use case for executing the delete cultural heritage command.
 */
public class ExecuteCommandUseCase implements IExecuteCommandUseCase {
    private ICulturalHeritageRepository repository;

    public ExecuteCommandUseCase(ICulturalHeritageRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(DeleteCulturalHeritageCommand command) {
        try {
            command.execute();
        } catch (RuntimeException e) {
            throw new RuntimeException("Use case execution failed: " + e.getMessage(), e);
        }
    }
}