package com.example.infrastructure;

/**
 * Implementation of StateRecoveryService.
 * For simplicity, we just log the restoration.
 */
public class StateRecoveryServiceImpl implements StateRecoveryService {
    public StateRecoveryServiceImpl() {
    }

    @Override
    public void restorePreviousState() {
        System.out.println("Previous state restored.");
    }
}