package com.instant.controller;

import com.instant.api.model.venue.Venue;
import com.instant.common.PaginationBean;
import com.instant.persistence.model.city.Cities;
import com.instant.persistence.model.city.City;
import com.instant.persistence.model.venue.VenueEntity;
import com.instant.persistence.model.venue.Venues;
import com.instant.persistence.repository.CityRepository;
import com.instant.persistence.repository.VenueRepository;
import com.instant.service.favourites.FavouritesService;
import com.instant.service.search.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.support.ConfigurableConversionService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;


/**
 * Search controller - Venue search, venue and city suggestion
 *
 * @author sroshchupkin
 */
@Controller
public class SearchController {

    @Autowired
    VenueRepository venueRepository;

    @Autowired
    CityRepository cityRepository;

    @Autowired
    private PaginationBean paginationBean;

    @Autowired
    SearchService searchService;

    @Autowired
    MongoOperations mongoOperations;

    @Autowired
    FavouritesService favouritesService;

    @Autowired
    private ConfigurableConversionService conversionService;

    /**
     * @param query       - search query
     * @param category    - category of venue
     * @param view        - view
     * @param pageNum     - page number, starts from 1
     * @param city        - city name
     * @param reviews     - reviews filter
     * @param speciality  - speciality filter
     * @param sortingType - sorting type
     * @return model and view
     */
    @RequestMapping(Mappings.CLIENTS)
    public ModelAndView search(@RequestParam("query") String query,
                               @RequestParam(value = "category", required = false, defaultValue = "") String category,
                               @RequestParam(value = "view", required = false, defaultValue = "1") Integer view,
                               @RequestParam(value = "page", required = false, defaultValue = "1") Integer pageNum,
                               @RequestParam(value = "city", required = false, defaultValue = "") String city,
                               @RequestParam(value = "reviews", required = false, defaultValue = "0") int reviews,
                               @RequestParam(value = "speciality", required = false, defaultValue = "") List<String> speciality,
                               @RequestParam(value = "sortingType", required = false, defaultValue = "") String sortingType) {
        ModelAndView modelAndView = new ModelAndView(TilesDefinition.HOME);

        if (view == 2) {
            modelAndView = new ModelAndView("fragments/itemlist_product_list :: itemlist_product_list");
        }
        if (StringUtils.isEmpty(query)) {
            List<VenueEntity> venueEntities = venueRepository.findAll(paginationBean.defaultPageable(pageNum - 1)).getContent();
            modelAndView.addObject("venues", venueEntities.stream().map(e -> conversionService.convert(e, Venue.class))
                    .collect(Collectors.toList()));
            return modelAndView;
        } else {
            Query searchQuery = searchService.getQuery(query, city, category, reviews, speciality,
                    sortingType, paginationBean.defaultPageable(pageNum - 1));
            List<VenueEntity> venueEntities = mongoOperations.find(searchQuery, VenueEntity.class);
            List<Venue> venues = favouritesService.checkAndGetVenues(venueEntities.stream().map(e -> conversionService.convert(e, Venue.class))
                    .collect(Collectors.toList()));
            modelAndView.addObject("venues", venues);
            modelAndView.addObject("searchString", query);
        }
        return modelAndView;
    }


    @RequestMapping(path = Mappings.VENUE_SUGGESTIONS, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Venues getVenueNameSuggestions(@RequestParam(value = "query") String query,
                                          @RequestParam(value = "city", required = false, defaultValue = "Berlin") String city,
                                          @RequestParam(value = "category", required = false, defaultValue = "ALL") String category) {
        Venues venues = new Venues();
        if (category.toUpperCase().equals("ALL") && city.equals("Berlin")) {
            venues.getVenues().addAll(venueRepository.findTop10ByNameLikeIgnoreCase(query).stream().map(e -> conversionService.convert(e, Venue.class))
                    .collect(Collectors.toList()));
        } else {
            venues.getVenues().addAll(venueRepository.
                    findTop10ByCityAndCategoryAndNameLikeIgnoreCase(city, category, query).stream().map(e -> conversionService.convert(e, Venue.class))
                    .collect(Collectors.toList()));
        }

        return venues;
    }

    @RequestMapping(path = Mappings.CITY_SUGGESTIONS, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Cities getCityNameSuggestions(@RequestParam(value = "query") String cityName) {
        Cities cities = new Cities();
        if (cityName.isEmpty()) {
            List<City> topTen = cityRepository.findAll(new PageRequest(0, 10)).getContent();
            cities.getCities().addAll(topTen);
        } else {
            cities.getCities().addAll(cityRepository.findTop10ByNameLikeIgnoreCase(cityName));
        }
        return cities;
    }
}
