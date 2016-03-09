package com.instant.service.venue;

import com.instant.api.model.venue.NewVenue;
import com.instant.api.model.venue.Venue;

import java.util.List;

/**
 * @author sroshchupkin
 */


public interface VenueService {

    List<Venue> getVenues(Integer limit, Integer offset);

    Venue saveVenue(NewVenue newVenue);

    Venue getVenueById(String id);

    Venue updateVenue(Venue venue);

    void deleteVenue(String id);
}
