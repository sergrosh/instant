package com.instant.service.venue;

import com.instant.persistence.model.venue.Venue;

import java.util.List;

/**
 * @author sroshchupkin
 */


public interface VenueService {

    List<Venue> getVenues(Integer limit, Integer offset);

    Venue saveVenue(Venue venue);

    Venue getVenueEntityById(String id);

    Venue updateVenue(Venue venue);

    void deleteVenue(String id);
}
