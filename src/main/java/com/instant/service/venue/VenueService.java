package com.instant.service.venue;

import com.instant.api.model.venue.NewVenue;
import com.instant.api.model.venue.Venue;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

/**
 * @author sroshchupkin
 */


public interface VenueService {

    List<Venue> findVenues(Integer limit, Integer offset);

    Venue saveVenue(NewVenue newVenue);

    Venue findVenueById(String id);

    Venue updateVenue(Venue venue);

    void updateRating(String id);

    void deleteVenue(String id);

    List<Venue> findByCityAndPublished(String city, int pageNum);

    List<Venue> findBySearchQuery(Query searchQuery);

    List<Venue> findExtraVenues(String city, int pageNum, int size);
}
