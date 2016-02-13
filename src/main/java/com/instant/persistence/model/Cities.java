package com.instant.persistence.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sroshchupkin
 */

@XmlRootElement
public class Cities {

    List<City> cities;

    @XmlElement
    public List<City> getCities() {
        if (cities == null) {
            cities = new ArrayList<>();
        }
        return cities;
    }
}
