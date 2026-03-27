package com.agency;

import java.util.List;

/**
 * Controller for searching and selecting tourists.
 */
public class SearchTouristController {
    private ITouristRepository touristRepository;
    private SearchCriteria searchCriteria;

    public SearchTouristController(ITouristRepository repo) {
        this.touristRepository = repo;
        this.searchCriteria = new SearchCriteria();
    }

    public ITouristRepository getTouristRepository() {
        return touristRepository;
    }

    public void setTouristRepository(ITouristRepository touristRepository) {
        this.touristRepository = touristRepository;
    }

    public SearchCriteria getSearchCriteria() {
        return searchCriteria;
    }

    public void setSearchCriteria(SearchCriteria searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    /**
     * Searches tourists based on criteria.
     * @return list of matching tourists.
     */
    public List<Tourist> searchTourists() {
        return touristRepository.findAll(searchCriteria);
    }

    /**
     * Searches tourists based on provided criteria.
     * @param criteria the search criteria.
     * @return list of matching tourists.
     */
    public List<Tourist> searchTourists(SearchCriteria criteria) {
        return touristRepository.findAll(criteria);
    }

    /**
     * Selects a tourist by ID.
     * @param touristId the ID of the tourist.
     * @return the selected tourist.
     * @throws TouristNotFoundException if tourist is not found.
     */
    public Tourist selectTourist(String touristId) {
        return touristRepository.findById(touristId)
                .orElseThrow(() -> new TouristNotFoundException("Tourist not found: " + touristId));
    }
}