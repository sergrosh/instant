package com.instant.service.favourites;

import com.instant.persistence.model.venue.Venue;

import java.util.List;

/**
 * @author sroshchupkin
 */
public interface FavouritesService {
    public List<Venue> checkAndGetVenues(List<Venue> venues);
}