package com.instant.controller.rest.venue;

import com.instant.api.controller.VenueRestController;
import com.instant.api.model.venue.NewVenue;
import com.instant.api.model.venue.Venue;
import com.instant.api.model.venue.VenueResponse;
import com.instant.controller.Mappings;
import com.instant.exception.InternalServerException;
import com.instant.persistence.model.venue.VenueEntity;
import com.instant.service.venue.VenueService;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Venue rest controller implementation - GET, POST, PUT, DELETE for CRUD operation for venues
 *
 * @author sroshchupkin
 */
@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:9000")
@RequestMapping(value = Mappings.REST + Mappings.VENUE)
public class VenueRestControllerImpl implements VenueRestController {

    @Autowired
    ConversionService conversionService;

    @Autowired
    VenueService venueService;

    @Override
    public VenueResponse getVenues(@ApiParam(value = "maximum number of results to return. Default is 20. Limit should be less then 1000.")
                                 @RequestParam(value = "limit", required = false, defaultValue = "20") Integer limit,

                                   @ApiParam(value = "This parameter specify where to start returning venues from the entire set of result. Default is 0.")
                                 @RequestParam(value = "offset",
                                         required = false,
                                         defaultValue = "0") Integer offset) {
        Page<VenueEntity> page;
        try {
            page = venueService.findVenues(limit,offset);
        } catch (Exception e) {
            log.error("internal error", e);
            throw new InternalServerException();
        }
        List<Venue> venues = page.getContent().stream().map(e -> conversionService.convert(e, Venue.class)).collect(Collectors.toList());
        return new VenueResponse.Builder().withData(venues).withTotal(page.getTotalElements()).build();
    }

    @Override
    public Venue createVenue(@ApiParam(value = "Venue to add to the system",
            required = true) @RequestBody NewVenue newVenue) {
        return venueService.saveVenue(newVenue);
    }

    @Override
    public Venue getVenue(@ApiParam(value = "id of venue to fetch", required = true)
                          @PathVariable("id") String id) {
        return venueService.findVenueById(id);
    }

    @Override
    public Venue updateVenue(@ApiParam(value = "venue that will be updated", required = true)
                             @RequestBody Venue venue) {
        return venueService.updateVenue(venue);
    }

    @Override
    public void deleteVenue(@ApiParam(value = "id of venue to delete",
            required = true) @PathVariable("id") String id) {
        venueService.deleteVenue(id);
    }
}
