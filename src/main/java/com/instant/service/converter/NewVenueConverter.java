package com.instant.service.converter;

import com.instant.api.model.venue.NewVenue;
import com.instant.persistence.model.venue.VenueEntity;
import com.instant.service.geo.GeoCoderService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Sergii Roshchupkin
 */
public final class NewVenueConverter extends AbstractVenueConverter<NewVenue> {

    @Autowired
    GeoCoderService geoCoderService;

    @Override
    public VenueEntity convert(NewVenue venue) {
        VenueEntity venueEntity = super.convert(venue);
        String address = venue.getAddress();
        venueEntity.setLocation(geoCoderService.getLocationFromAddress(address));
        return venueEntity;
    }

}
