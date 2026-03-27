
package com.example.transaction;

import javax.persistence.EntityManager;

/**
 * Manages database transactions.
 */
public class TransactionManager {

    private EntityManager entityManager;

    public TransactionManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Executes the given operation within a transaction.
     * @param operation the operation to run.
     */
    public void executeInTransaction(Runnable operation) {
        // Simulate transaction begin
        System.out.println("TransactionManager: Starting transaction.");
        try {
            entityManager.getTransaction().begin();
            operation.run();
            // Simulate commit
            entityManager.getTransaction().commit();
            System.out.println("TransactionManager: Committing transaction.");
        } catch (Exception e) {
            // Explicit rollback transaction on version conflict as per sequence diagram
            rollbackTransaction();
            throw e; // Re-throw to inform caller
        }
    }

    /**
     * Rolls back the current transaction.
     */
    public void rollbackTransaction() {
        if (entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().rollback();
        }
        // Simulate rollback
        System.out.println("TransactionManager: Rolling back transaction.");
    }
}
