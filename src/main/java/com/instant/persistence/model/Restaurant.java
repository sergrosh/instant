package com.instant.persistence.model;


import org.springframework.data.annotation.Id;

/**
 * @author sroshchupkin
 */
public class Restaurant {
    @Id
    private String id;

    String name;
    String address;

    public Restaurant(){}

    public Restaurant(String name, String address){}

    @Override
    public String toString() {
        return String.format("Customer[id=%s, firstName='%s', lastName='%s']", id,
                name, address);
    }


}
