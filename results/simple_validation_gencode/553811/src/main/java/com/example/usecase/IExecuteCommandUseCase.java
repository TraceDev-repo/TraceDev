package com.example.usecase;

import com.example.usecase.command.DeleteCulturalHeritageCommand;

/**
 * Interface for executing commands.
 */
public interface IExecuteCommandUseCase {
    void execute(DeleteCulturalHeritageCommand command);
}