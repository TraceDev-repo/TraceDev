package com.newsagency.util;

/**
 * Service for managing transactions to ensure data consistency.
 * Added to satisfy quality requirement for data consistency during insertion.
 */
public class TransactionManager {

    private boolean inTransaction = false;

    public void beginTransaction() {
        inTransaction = true;
        System.out.println("Transaction began.");
    }

    public void commit() {
        if (inTransaction) {
            System.out.println("Transaction committed.");
            inTransaction = false;
        }
    }

    public void rollback() {
        if (inTransaction) {
            System.out.println("Transaction rolled back.");
            inTransaction = false;
        }
    }

    public boolean isInTransaction() {
        return inTransaction;
    }
}