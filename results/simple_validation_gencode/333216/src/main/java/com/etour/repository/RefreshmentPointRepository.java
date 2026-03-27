package com.etour.repository;

import com.etour.datasource.IDataSource;
import com.etour.model.RefreshmentPoint;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Database repository for RefreshmentPoint.
 */
public class RefreshmentPointRepository implements IRefreshmentPointRepository {
    private IDataSource dataSource;

    public RefreshmentPointRepository(IDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public RefreshmentPoint findById(String id) {
        System.out.println("Repository finding point by ID: " + id);
        // Simulate database query
        String sql = "SELECT * FROM refreshment_point WHERE id = '" + id + "'";
        try {
            ResultSet rs = dataSource.query(sql);
            if (rs != null && rs.next()) {
                RefreshmentPoint point = new RefreshmentPoint();
                point.setId(rs.getString("id"));
                point.setName(rs.getString("name"));
                point.setAddress(rs.getString("address"));
                // Simulate amenities list
                List<String> amenities = new ArrayList<>();
                amenities.add("WiFi");
                amenities.add("Restrooms");
                point.setAmenities(amenities);
                point.setLastUpdated(new Date());
                return point;
            }
        } catch (Exception e) {
            System.err.println("Error querying database: " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean save(RefreshmentPoint point) {
        System.out.println("Repository saving point: " + point.getId());
        // Simulate database update
        String sql = "UPDATE refreshment_point SET address = '" + point.getAddress() + "' WHERE id = '" + point.getId() + "'";
        try {
            int rows = dataSource.update(sql);
            return rows > 0;
        } catch (Exception e) {
            System.err.println("Error updating database: " + e.getMessage());
            return false;
        }
    }
}