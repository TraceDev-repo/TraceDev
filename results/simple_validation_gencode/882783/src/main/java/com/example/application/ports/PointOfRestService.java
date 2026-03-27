package com.example.application.ports;

import java.util.Map;

/**
 * Port (Interface) for fetching data from the point of rest.
 */
public interface PointOfRestService {
    Map<String, Object> fetchConventionRequestData(String conventionId);
}