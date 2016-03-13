package com.instant.service.favourites;

import com.instant.api.model.venue.Venue;

import java.util.List;

/**
 * @author sroshchupkin
 */
public interface FavouritesService {
    List<Venue> checkAndGetVenues(List<Venue> venues);
}
