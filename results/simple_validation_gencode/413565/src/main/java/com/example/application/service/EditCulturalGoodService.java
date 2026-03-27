package com.example.application.service;

import com.example.application.port.in.EditCulturalGoodUseCase;
import com.example.application.port.out.CulturalGoodRepository;
import com.example.domain.CulturalGood;
import com.example.infrastructure.ServerConnection;
import java.util.Date;
import java.util.Map;

/**
 * Application service implementing the edit cultural good use case.
 */
public class EditCulturalGoodService implements EditCulturalGoodUseCase {
    private CulturalGoodRepository culturalGoodRepository;
    private boolean transactionLock = false;
    private ServerConnection serverConnection;

    public EditCulturalGoodService(CulturalGoodRepository repository, ServerConnection serverConnection) {
        this.culturalGoodRepository = repository;
        this.serverConnection = serverConnection;
    }

    @Override
    public CulturalGood loadCulturalGood(String id) {
        return culturalGoodRepository.findById(id);
    }

    @Override
    public boolean updateCulturalGood(String id, Map<String, Object> updatedData) {
        // Lock acquisition moved to controller level.
        // The lock is already acquired before this call.
        // So we only need to validate data and perform update.
        if (!serverConnection.checkConnection()) {
            return false;
        }
        if (!validateData(updatedData)) {
            return false;
        }
        CulturalGood culturalGood = culturalGoodRepository.findById(id);
        if (culturalGood == null) {
            return false;
        }
        culturalGood.updateData(updatedData);
        culturalGood.setLastModifiedDate(new Date());
        culturalGoodRepository.save(culturalGood);
        return true;
    }

    @Override
    public boolean requestConfirmation(String operation) {
        // This method is deprecated; confirmation is handled by ConfirmationHandler.
        // Return true to keep backward compatibility.
        return true;
    }

    private boolean acquireLock() {
        synchronized (this) {
            if (transactionLock) {
                return false;
            }
            transactionLock = true;
            return true;
        }
    }

    private void releaseLock() {
        synchronized (this) {
            transactionLock = false;
        }
    }

    public boolean acquireLockForUpdate() {
        return acquireLock();
    }

    public void releaseLockForUpdate() {
        releaseLock();
    }

    private boolean validateData(Map<String, Object> data) {
        return data != null && !data.isEmpty();
    }
}