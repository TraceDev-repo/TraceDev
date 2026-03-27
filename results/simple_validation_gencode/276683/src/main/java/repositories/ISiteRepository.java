package repositories;

import entities.Site;
import java.util.List;

/**
 * Repository interface for Site entities as per the class diagram.
 */
public interface ISiteRepository {
    /**
     * Finds a site by its identifier.
     * @param siteId the site identifier
     * @return the Site object, null if not found
     */
    Site findById(String siteId);

    /**
     * Retrieves all sites.
     * @return a list of all sites
     */
    List<Site> findAll();
}