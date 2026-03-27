package com.example.adapter.presentation;

import com.example.domain.CulturalGood;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Form model for cultural good editing.
 */
public class CulturalGoodForm {
    private String id;
    private String name;
    private String description;
    private String category;
    private String location;
    private Date lastModifiedDate;

    public CulturalGoodForm() {}

    public CulturalGoodForm(CulturalGood culturalGood) {
        this.id = culturalGood.getId();
        this.name = culturalGood.getName();
        this.description = culturalGood.getDescription();
        this.category = culturalGood.getCategory();
        this.location = culturalGood.getLocation();
        this.lastModifiedDate = culturalGood.getLastModifiedDate();
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("name", name);
        map.put("description", description);
        map.put("category", category);
        map.put("location", location);
        map.put("lastModifiedDate", lastModifiedDate);
        return map;
    }

    public void fromMap(Map<String, Object> data) {
        if (data.containsKey("id")) {
            this.id = (String) data.get("id");
        }
        if (data.containsKey("name")) {
            this.name = (String) data.get("name");
        }
        if (data.containsKey("description")) {
            this.description = (String) data.get("description");
        }
        if (data.containsKey("category")) {
            this.category = (String) data.get("category");
        }
        if (data.containsKey("location")) {
            this.location = (String) data.get("location");
        }
        if (data.containsKey("lastModifiedDate")) {
            this.lastModifiedDate = (Date) data.get("lastModifiedDate");
        }
    }

    public boolean validate() {
        return name != null && !name.trim().isEmpty();
    }

    public String getName() {
        return name;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}