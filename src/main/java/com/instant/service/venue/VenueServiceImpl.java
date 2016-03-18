package com.instant.service.venue;

import com.instant.api.model.venue.NewVenue;
import com.instant.api.model.venue.Venue;
import com.instant.common.PaginationBean;
import com.instant.exception.InternalServerException;
import com.instant.exception.InvalidRequestException;
import com.instant.exception.NotFoundException;
import com.instant.persistence.model.venue.VenueEntity;
import com.instant.persistence.repository.VenueRepository;
import com.instant.service.counter.CounterService;
import com.instant.service.favourites.FavouritesService;
import com.instant.service.geo.GeoCoderService;
import com.instant.service.user.UserAccountService;
import com.instant.service.validator.ValidationResult;
import com.instant.service.validator.ValidationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.support.ConfigurableConversionService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author sroshchupkin
 */

@Slf4j
@Service
public class VenueServiceImpl implements VenueService {

    @Autowired
    VenueRepository venueRepository;

    @Autowired
    ValidationService<NewVenue> validationService;

    @Autowired
    GeoCoderService geoCoderService;

    @Autowired
    CounterService counterService;

    @Autowired
    private ConfigurableConversionService conversionService;

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private PaginationBean paginationBean;

    @Autowired
    MongoOperations mongoOperations;

    @Autowired
    FavouritesService favouritesService;


    @Override
    public List<Venue> findVenues(Integer limit, Integer offset) {
        List<Venue> venues;
        try {
            venues = venueRepository.findAll(new PageRequest(offset, limit)).getContent()
                    .stream().map(e -> conversionService.convert(e, Venue.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            log.error("internal error", e);
            throw new InternalServerException();
        }
        return venues;
    }

    @Override
    public Venue saveVenue(NewVenue newVenue) {
        ValidationResult result = validationService.validate(newVenue);
        if (result.isFailed()) {
            log.error("validation failed: {}", result);
            throw new InvalidRequestException();
        }
        VenueEntity venueEntity = conversionService.convert(newVenue, VenueEntity.class);
        //venue.setId(counterService.getNextSequence("Venue"));

        venueEntity.setUserId(userAccountService.getCurrentUser().getUserId());
        if (!newVenue.getAddress().isEmpty()) {
            double[] geoPoint = geoCoderService.getLocationFromAddress(newVenue.getAddress());
            venueEntity.setLocation(geoPoint);
        }
        venueEntity.setPublished(false);
        try {
            venueRepository.save(venueEntity);
        } catch (Exception e) {
            throw new InternalServerException();
        }
        return conversionService.convert(venueEntity, Venue.class);
    }


    @Override
    public Venue findVenueById(String id) {
        VenueEntity venueEntity;
        try {
            venueEntity = venueRepository.findOne(id);
        } catch (Exception e) {
            log.error("Internal exception", e);
            throw new InternalServerException();
        }

        if (venueEntity == null) {
            log.error("Venue with id {} not found", id);
            throw new NotFoundException();
        }
        return conversionService.convert(venueEntity, Venue.class);
    }

    @Override
    public Venue updateVenue(Venue venue) {
        ValidationResult result = validationService.validate(venue);
        if (result.isFailed()) {
            log.error("validation failed: {}", result);
            throw new InvalidRequestException();
        }
        VenueEntity venueEntity = conversionService.convert(venue, VenueEntity.class);
        try {
            venueEntity = venueRepository.save(venueEntity);
        } catch (Exception e) {
            log.error("Internal error", e);
            throw new InternalServerException();
        }
        return conversionService.convert(venueEntity, Venue.class);
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

    @Override
    public List<Venue> findByCityAndPublished(String city, int pageNum) {
        List<VenueEntity> venueEntities = venueRepository.findByCityAndPublished(city, true, paginationBean.defaultPageable(pageNum - 1)).getContent();
        List<Venue> venues = favouritesService.checkAndGetVenues(
                venueEntities.stream().map(e -> conversionService.convert(e, Venue.class)).collect(Collectors.toList()));
        return venues;
    }

    @Override
    public List<Venue> findBySearchQuery(Query searchQuery) {
        List<VenueEntity> venueEntities = mongoOperations.find(searchQuery, VenueEntity.class);
        List<Venue> venues = favouritesService.checkAndGetVenues(
                venueEntities.stream().map(e -> conversionService.convert(e, Venue.class))
                        .collect(Collectors.toList()));
        return venues;
    }

    public List<Venue> findExtraVenues(String city, int pageNum, int size) {
        List<VenueEntity> extraVenueEntities = venueRepository.findByCity(city,
                new PageRequest(pageNum, paginationBean.getPageSize() - size, new Sort(Sort.Direction.DESC, "name"))).getContent();
        List<Venue> extraVenues = favouritesService.checkAndGetVenues(extraVenueEntities.stream().map(e -> conversionService.convert(e, Venue.class))
                .collect(Collectors.toList()));
        return extraVenues;
    }


}
