package com.example.application;

import java.util.List;

/**
 * DTO containing search results and metadata.
 */
public class SearchResultDTO {
    private final List<CulturalObjectDTO> results;
    private final int totalCount;
    private final long searchTimeMs;

    public SearchResultDTO(List<CulturalObjectDTO> results, int totalCount, long searchTimeMs) {
        this.results = results;
        this.totalCount = totalCount;
        this.searchTimeMs = searchTimeMs;
    }

    public List<CulturalObjectDTO> getResults() {
        return results;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public long getSearchTimeMs() {
        return searchTimeMs;
    }

    @Override
    public String toString() {
        return "SearchResultDTO{" +
                "totalCount=" + totalCount +
                ", searchTimeMs=" + searchTimeMs +
                '}';
    }
}