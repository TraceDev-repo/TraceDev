package com.example.repository;

import com.example.dto.ConventionDTO;
import java.util.List;

// Repository interface for convention data access
public interface IConventionRepository {
    List<ConventionDTO> getHistoryByAgencyId(int agencyId);
}