package com.example.infrastructure;

/**
 * Represents a state snapshot for recovery.
 */
public class StateSnapshot {
    private String id;
    private String data;

    public StateSnapshot(String id, String data) {
        this.id = id;
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public String getData() {
        return data;
    }
}