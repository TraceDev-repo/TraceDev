package com.example.infrastructure;

/**
 * Repository interface for StateSnapshot entities.
 */
public interface StateSnapshotRepository {
    StateSnapshot findLatest();
    void save(StateSnapshot snapshot);
}