package com.instant.controller.rest.venue;

import com.instant.api.controller.VenueRestController;
import com.instant.controller.Mappings;
import com.instant.exception.InternalServerException;
import com.instant.exception.InvalidRequestException;
import com.instant.exception.NotFoundException;
import com.instant.persistence.model.venue.Venue;
import com.instant.persistence.repository.VenueRepository;
import com.instant.service.venue.VenueService;
import com.instant.validator.ValidationResult;
import com.instant.validator.ValidationService;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Venue rest controller implementation - GET, POST, PUT, DELETE for CRUD operation for venues
 *
 * @author sroshchupkin
 */
@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:9000")
@RequestMapping(value = Mappings.REST_VENUE)
public class VenueRestControllerImpl implements VenueRestController {

    @Autowired
    VenueService venueService;

    @Override
    public List<Venue> getVenues(@ApiParam(value = "maximum number of results to return. Default is 20. Limit should be less then 1000.") @RequestParam(value = "limit",
            required = false,
            defaultValue = "20") Integer limit, @ApiParam(value = "This parameter specify where to start returning venues from the entire set of result. Default is 0.") @RequestParam(value = "offset",
            required = false,
            defaultValue = "0") Integer offset) {
        return venueService.getVenues(limit,offset);
    }

    @Override
    public Venue createVenue(@ApiParam(value = "Venue to add to the system",
            required = true) @RequestBody Venue venue) {
        return venueService.saveVenue(venue);
    }

    @Override
    public Venue getVenue(@ApiParam(value = "id of venue to fetch", required = true) @PathVariable("id") String id) {
        return venueService.getVenueEntityById(id);
    }

    @Override
    public Venue updateVenue(@ApiParam(value = "venue that will be updated", required = true) @RequestBody Venue venue) {
        return  venueService.updateVenue(venue);
    }

    @Override
    public void deleteVenue(@ApiParam(value = "id of venue to delete",
            required = true) @PathVariable("id") String id) {
        venueService.deleteVenue(id);
    }

}
