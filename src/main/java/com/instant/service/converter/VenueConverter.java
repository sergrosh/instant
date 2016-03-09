package com.instant.service.converter;

import com.instant.api.model.venue.Venue;
import com.instant.persistence.model.venue.VenueEntity;

/**
 * converter - Venue -> VenueEntity
 *
 * @author Sergii Roshchupkin
 */
public final class VenueConverter extends AbstractVenueConverter<Venue> {

    @Override
    public VenueEntity convert(Venue venue) {
        VenueEntity venueEntity = super.convert(venue);
        venueEntity.setId(venue.getId());
        venueEntity.setLocation(venue.getLocation());
        venueEntity.setRating(venue.getRating());
        venueEntity.setReviews(venue.getReviews());
        venueEntity.setLikes(venue.getLikes());
        return venueEntity;
    }
}
