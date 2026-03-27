package com.etour.datasource;

import java.sql.ResultSet;

/**
 * Interface for database data source operations.
 */
public interface IDataSource {
    boolean connect();
    ResultSet query(String sql);
    int update(String sql);
    void disconnect();
}