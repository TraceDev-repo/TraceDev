package repositories.impl;

import entities.Site;
import repositories.ISiteRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of ISiteRepository as per the class diagram.
 * Uses an in-memory data source for simplicity.
 * In a real application, this would connect to a database.
 */
public class SiteRepositoryImpl implements ISiteRepository {
    private Object dataSource; // Placeholder as per diagram; not used in this simple implementation
    private List<Site> siteList;

    /**
     * Constructor initializes the in-memory data source.
     */
    public SiteRepositoryImpl() {
        // Simulate a data source with some dummy data
        siteList = new ArrayList<>();
        siteList.add(new Site("SITE001", "Central Park", "New York"));
        siteList.add(new Site("SITE002", "Eiffel Tower", "Paris"));
        siteList.add(new Site("SITE003", "Sydney Opera", "Sydney"));
        dataSource = siteList; // initialize dataSource to reflect actual usage
    }

    public Site findById(String siteId) {
        Optional<Site> result = siteList.stream()
                .filter(site -> site.getSiteId().equals(siteId))
                .findFirst();
        return result.orElse(null);
    }

    public List<Site> findAll() {
        return new ArrayList<>(siteList);
    }

    public void setDataSource(Object dataSource) {
        this.dataSource = dataSource;
    }
}