package com.instant.controller.rest;

import com.instant.exception.InternalServerException;
import com.instant.persistence.model.venue.Venue;
import com.instant.persistence.repository.VenueRepository;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Venue rest controller implementation - GET, POST, PUT, DELETE for CRUD operation for venues
 *
 * @author sroshchupkin
 */
@Slf4j
public class VenueRestControllerImpl implements VenueRestController{

    @Autowired
    VenueRepository venueRepository;

    @Override
    public List<Venue> getVenues(@ApiParam(value = "maximum number of results to return. Default is 20. Limit should be less then 1000.") @RequestParam(value = "limit",
            required = false,
            defaultValue = "20") Integer limit, @ApiParam(value = "This parameter specify where to start returning venues from the entire set of result. Default is 0.") @RequestParam(value = "offset",
            required = false,
            defaultValue = "0") Integer offset) {
        List<Venue> venues;
        try {
            venues = venueRepository.findAll(new PageRequest(offset, limit)).getContent();
        } catch (Exception e) {
            log.error("internal error", e);
            throw new InternalServerException();
        }
        return venues;
    }


    @Override
    public Venue createVenue(@ApiParam(value = "Venue to add to the system",
            required = true) @RequestBody Venue venue) {
        return null;
    }


    @Override
    public Venue getVenue(@ApiParam(value = "id of venue to fetch", required = true) @PathVariable("id") Long id) {
        return null;
    }


    @Override
    public Venue updateVenue(@ApiParam(value = "venue that will be updated", required = true) @RequestBody Venue venue) {
        return null;
    }


    @Override
    public void deleteVenue(@ApiParam(value = "id of venue to delete",
            required = true) @PathVariable("id") Long id) {

    }
}
