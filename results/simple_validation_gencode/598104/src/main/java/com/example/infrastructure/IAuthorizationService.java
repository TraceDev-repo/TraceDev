package com.example.infrastructure;

import com.example.domain.AgencyOperator;

/**
 * Port interface for authorization service.
 */
public interface IAuthorizationService {
    boolean hasPermission(AgencyOperator operator, String permission);
}