package com.agency.reporting.application;

import com.agency.reporting.domain.StatisticalReport;

/**
 * Use case interface for generating a statistical report.
 */
public interface GenerateReportUseCase {
    StatisticalReport execute(String locationId);
}