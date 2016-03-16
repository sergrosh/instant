package com.instant.controller.rest.search;

import com.instant.api.controller.SearchRestController;
import com.instant.api.model.venue.Venue;
import com.instant.common.PaginationBean;
import com.instant.controller.Mappings;
import com.instant.persistence.model.venue.VenueEntity;
import com.instant.persistence.repository.CityRepository;
import com.instant.persistence.repository.VenueRepository;
import com.instant.service.favourites.FavouritesService;
import com.instant.service.search.SearchService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.support.ConfigurableConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author sroshchupkin
 */

@RestController
public class SearchRestControllerImpl implements SearchRestController{

    @Autowired
    VenueRepository venueRepository;
    @Autowired
    SearchService searchService;
    @Autowired
    MongoOperations mongoOperations;
    @Autowired
    FavouritesService favouritesService;
    @Autowired
    private ConfigurableConversionService conversionService;
    @Autowired
    private PaginationBean paginationBean;

    @Override
    public List<Venue> search(@RequestParam(value = "query", required = false) String query,
                              @RequestParam(value = "category", required = false, defaultValue = "") String category,
                              @RequestParam(value = "page", required = false, defaultValue = "1") Integer pageNum,
                              @RequestParam(value = "city", required = false, defaultValue = "Berlin") String city,
                              @RequestParam(value = "reviews", required = false, defaultValue = "0") int reviews,
                              @RequestParam(value = "speciality", required = false, defaultValue = "") List<String> speciality,
                              @RequestParam(value = "sortingType", required = false, defaultValue = "") String sortingType) {
        if (StringUtils.isEmpty(query)) {
            List<VenueEntity> venueEntities = venueRepository.findByCityAndPublished(city, true, paginationBean.defaultPageable(pageNum - 1))
                    .getContent();
            return venueEntities.stream().map(e -> conversionService.convert(e, Venue.class))
                    .collect(Collectors.toList());

        } else {
            Query searchQuery = searchService.getQuery(query, city, category, reviews, speciality,
                    sortingType, paginationBean.defaultPageable(pageNum - 1));
            List<VenueEntity> venueEntities = mongoOperations.find(searchQuery, VenueEntity.class);

            List<Venue> venues = favouritesService.checkAndGetVenues(venueEntities.stream().map(e -> conversionService.convert(e, Venue.class))
                    .collect(Collectors.toList()));
           return venues;

            }
    }
}
