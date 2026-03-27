package com.agency.service;

import com.agency.entity.AgencyOperator;

/**
 * Service responsible for operation authorization.
 */
public class AuthorizationService {
    public boolean isAuthorized(String operatorId, String permission) {
        // Simplified authorization: only MANAGE_BANNERS permission is allowed
        return "MANAGE_BANNERS".equals(permission);
    }

    public boolean validateOperation(AgencyOperator operator, String operation) {
        // Validate that the operator can perform the given operation
        return operator != null && operator.isAuthenticated() &&
                (operation.equals("DELETE_BANNER") && operator.hasPermission("MANAGE_BANNERS"));
    }
}