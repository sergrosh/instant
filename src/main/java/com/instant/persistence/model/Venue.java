package com.instant.persistence.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * @author sroshchupkin
 */
@Document
public class Venue {

    @Id
    private String id;

    String name;
    private List<String> typeTags;
    String address;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getTypeTags() {
        return typeTags;
    }

    public void setTypeTags(List<String> typeTags) {
        this.typeTags = typeTags;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


}
