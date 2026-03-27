package com.example.application;

/**
 * Port interface for the use case of updating tourist account status.
 */
public interface IUpdateTouristAccountStatusUseCase {
    void execute(UpdateTouristAccountStatusCommand command);
}