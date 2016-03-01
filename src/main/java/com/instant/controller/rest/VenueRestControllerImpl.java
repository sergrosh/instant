package com.instant.controller.rest;

import com.instant.exception.InternalServerException;
import com.instant.exception.InvalidRequestException;
import com.instant.exception.NotFoundException;
import com.instant.persistence.model.venue.Venue;
import com.instant.persistence.repository.VenueRepository;
import com.instant.validator.ValidationResult;
import com.instant.validator.ValidationService;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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

    @Autowired
    ValidationService<Venue> validationService;

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

        ValidationResult result = validationService.validate(venue);
        if (result.isFailed()) {
            log.error("validation failed: {}", result);
            throw new InvalidRequestException();
        }
        try{venueRepository.save(venue);}
        catch (Exception e){throw new InternalServerException();}
        return venue;
    }

    @Override
    public Venue getVenue(@ApiParam(value = "id of venue to fetch", required = true) @PathVariable("id") String id) {
        return getVenueEntityById(id);
    }

    @Override
    public Venue updateVenue(@ApiParam(value = "venue that will be updated", required = true) @RequestBody Venue venue) {
        ValidationResult result = validationService.validate(venue);
        if (result.isFailed()) {
            log.error("validation failed: {}", result);
            throw new InvalidRequestException();
        }
        try {
            venue = venueRepository.save(venue);
        } catch (Exception e) {
            log.error("Internal error", e);
            throw new InternalServerException();
        }
        return venue;
    }

    @Override
    public void deleteVenue(@ApiParam(value = "id of venue to delete",
            required = true) @PathVariable("id") String id) {
        try {
            venueRepository.delete(id);
        } catch (EmptyResultDataAccessException e) {
            log.error("Venue with id {} not found", id);
            throw new NotFoundException();
        } catch (Exception e) {
            log.error("Intenal server error", e);
            throw new InternalServerException();
        }
    }

    private Venue getVenueEntityById(String id) {
        Venue venue;
        try {
            venue = venueRepository.findOne(id);
        } catch (Exception e) {
            log.error("Internal exception", e);
            throw new InternalServerException();
        }

        if (venue == null) {
            log.error("Venue with id {} not found", id);
            throw new NotFoundException();
        }
        return venue;
    }
}
