package com.example.usecase;

import com.example.domain.CulturalHeritage;
import com.example.domain.dto.CulturalHeritageDTO;
import com.example.domain.repository.ICulturalHeritageRepository;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Use case for searching cultural heritages.
 * Returns a list of CulturalHeritageDTO objects for display.
 */
public class SearchCulturalHeritageUseCase {
    private ICulturalHeritageRepository repository;

    public SearchCulturalHeritageUseCase(ICulturalHeritageRepository repository) {
        this.repository = repository;
    }

    /**
     * Executes the search use case.
     * @return List of CulturalHeritageDTO objects.
     */
    public List<CulturalHeritageDTO> execute() {
        List<CulturalHeritage> heritages = repository.findAll();
        return convertToDTO(heritages);
    }

    /**
     * Converts a list of CulturalHeritage entities to CulturalHeritageDTO objects.
     * @param heritages List of CulturalHeritage entities.
     * @return List of CulturalHeritageDTO objects.
     */
    private List<CulturalHeritageDTO> convertToDTO(List<CulturalHeritage> heritages) {
        return heritages.stream()
                .map(heritage -> new CulturalHeritageDTO(heritage.getId(), heritage.getName(), heritage.getDescription()))
                .collect(Collectors.toList());
    }
}