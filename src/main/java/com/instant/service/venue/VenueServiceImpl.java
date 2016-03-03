package com.instant.service.venue;

import com.instant.exception.InternalServerException;
import com.instant.exception.InvalidRequestException;
import com.instant.exception.NotFoundException;
import com.instant.persistence.model.venue.Venue;
import com.instant.persistence.repository.VenueRepository;
import com.instant.service.geo.GeoCoderService;
import com.instant.validator.ValidationResult;
import com.instant.validator.ValidationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author sroshchupkin
 */

@Slf4j
@Service
public class VenueServiceImpl implements VenueService {

    @Autowired
    VenueRepository venueRepository;

    @Autowired
    ValidationService<Venue> validationService;

    @Autowired
    GeoCoderService geoCoderService;


    @Override
    public List<Venue> getVenues(Integer limit, Integer offset) {
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
    public Venue saveVenue(Venue venue) {
        ValidationResult result = validationService.validate(venue);
        if (result.isFailed()) {
            log.error("validation failed: {}", result);
            throw new InvalidRequestException();
        }
        if (!venue.getAddress().isEmpty()) {
            Point geoPoint = geoCoderService.getGeoPointFromAddress(venue.getAddress());
            venue.setLocation(geoPoint);
        }

        try {
            venueRepository.save(venue);
        } catch (Exception e) {
            throw new InternalServerException();
        }
        return venue;
    }


    @Override
    public Venue getVenueEntityById(String id) {
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

    @Override
    public Venue updateVenue(Venue venue) {
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
    public void deleteVenue(String id) {
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

}
