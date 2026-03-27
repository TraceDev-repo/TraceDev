package com.example.application;

import com.example.domain.RefreshmentPoint;
import com.example.domain.SearchSpecification;
import com.example.domain.repository.RefreshmentPointRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Use case for searching refreshment points.
 * This class belongs to the Application Layer (Application Business Rules).
 */
public class SearchRefreshmentPointsUseCase {
    private final RefreshmentPointRepository repository;

    public SearchRefreshmentPointsUseCase(RefreshmentPointRepository repository) {
        this.repository = repository;
    }

    /**
     * Executes the search use case based on the provided query.
     *
     * @param query the search query
     * @return a list of RefreshmentPointDto objects representing the results
     */
    public List<RefreshmentPointDto> execute(SearchRefreshmentPointsQuery query) {
        // Convert query to domain SearchSpecification
        SearchSpecification spec = new SearchSpecification();
        spec.setLocation(query.getLocationFilter());
        spec.setPointType(query.getTypeFilter());
        spec.setRatingThreshold(query.getMinRating());
        spec.setRequiredAmenities(query.getAmenities());

        // Retrieve domain entities from repository
        List<RefreshmentPoint> points = repository.findAllBySpecification(spec);

        // Map domain entities to DTOs
        return points.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private RefreshmentPointDto mapToDto(RefreshmentPoint point) {
        RefreshmentPointDto dto = new RefreshmentPointDto();
        dto.setId(point.getId());
        dto.setName(point.getName());
        dto.setLocation(point.getLocation());
        dto.setType(point.getType());
        dto.setAmenities(point.getAmenities());
        dto.setRating(point.getRating());
        return dto;
    }
}