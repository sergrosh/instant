package com.instant.persistence.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * @author sroshchupkin
 */


@Document
public class City {

    @Id
    private String id;
    private String name;
    private List<String> zip;

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

    public List<String> getZip() {
        return zip;
    }

    public void setZip(List<String> zip) {
        this.zip = zip;
    }
}
