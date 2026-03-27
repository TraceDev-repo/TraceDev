package com.example.infrastructure;

import com.example.domain.AccountStatus;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Simulates a confirmation dialog as per REQ-FLOW-02.
 * In a real system, this would be a UI component.
 */
public class ConfirmationDialog implements IConfirmationService {

    private String accountId;
    private AccountStatus newStatus;
    private boolean result;
    private PerformanceMonitor performanceMonitor;

    public ConfirmationDialog(String accountId, AccountStatus newStatus) {
        this.accountId = accountId;
        this.newStatus = newStatus;
    }

    /**
     * Shows a simulated confirmation dialog.
     * In reality, this would block until user responds.
     * Here we simulate user interaction with timeout and cancellation scenarios.
     */
    public boolean showDialog() {
        System.out.println("Confirmation dialog shown for account: " + accountId +
                " to change status to: " + newStatus);
        
        // Simulate blocking for user input with timeout
        try {
            // Simulate waiting for user action (e.g., 500ms)
            Thread.sleep(500);
            
            // Performance constraint validation during dialog display
            if (performanceMonitor != null) {
                performanceMonitor.checkTimeout();
            }
            
            // Simulate user choice - 90% confirm, 10% cancel
            double random = Math.random();
            if (random < 0.9) {
                this.result = true;
                System.out.println("User confirmed the operation.");
            } else {
                this.result = false;
                System.out.println("User cancelled the operation.");
            }
            
            // Simulate alt branch from sequence diagram: user does nothing
            // In real UI, this would be handled by a timeout
            if (random > 0.95) {
                System.out.println("User did not respond - simulating timeout.");
                throw new java.util.concurrent.TimeoutException("Dialog timeout - no user response.");
            }
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            this.result = false;
            System.out.println("Dialog interrupted.");
        } catch (java.util.concurrent.TimeoutException e) {
            this.result = false;
            System.out.println("Dialog timeout.");
            throw new RuntimeException("Dialog timed out waiting for user response.", e);
        }
        
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
    
    public void setPerformanceMonitor(PerformanceMonitor performanceMonitor) {
        this.performanceMonitor = performanceMonitor;
    }

    @Override
    public boolean requestConfirmation(String accountId, AccountStatus newStatus) {
        this.accountId = accountId;
        this.newStatus = newStatus;
        return showDialog();
    }
}