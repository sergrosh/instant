package com.instant.persistence.model.venue;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sroshchupkin
 */

@XmlRootElement
public class Venues {

    List<Venue> venues;

    @XmlElement
    public List<Venue> getVenues() {
        if (venues == null) {
            venues = new ArrayList<>();
        }
        return venues;
    }
}