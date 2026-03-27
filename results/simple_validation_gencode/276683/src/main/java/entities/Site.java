package entities;

import java.util.Objects;

/**
 * Represents a Site entity as per the class diagram.
 * Contains siteId, name, and location.
 */
public class Site {
    private String siteId;
    private String name;
    private String location;

    /**
     * Constructor for Site.
     * @param siteId the site identifier
     * @param name the name of the site
     * @param location the location of the site
     */
    public Site(String siteId, String name, String location) {
        this.siteId = siteId;
        this.name = name;
        this.location = location;
    }

    public String getSiteId() {
        return siteId;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Site site = (Site) o;
        return Objects.equals(siteId, site.siteId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(siteId);
    }

    @Override
    public String toString() {
        return "Site{" +
                "siteId='" + siteId + '\'' +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}