package com.example.infrastructure;

import java.util.Date;

/**
 * Value object for data statistics.
 */
public class DataStatistics {
    private final int totalObjects;
    private final Date lastUpdate;

    public DataStatistics(int totalObjects, Date lastUpdate) {
        this.totalObjects = totalObjects;
        this.lastUpdate = lastUpdate;
    }

    public int getTotalObjects() {
        return totalObjects;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }
}