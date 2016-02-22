package com.instant.controller;

import com.instant.common.PaginationBean;
import com.instant.persistence.model.city.Cities;
import com.instant.persistence.model.city.City;
import com.instant.persistence.model.venue.Venue;
import com.instant.persistence.model.venue.Venues;
import com.instant.persistence.repository.CityRepository;
import com.instant.persistence.repository.VenueRepository;
import com.instant.service.search.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
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


/**
 *
 *  Search controller - Venue search, venue and city suggestion
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

    /**
     *
     * @param query -
     * @param category
     * @param view
     * @param pageNum
     * @param city
     * @param reviews
     * @param speciality
     * @param sortingType
     * @return
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
            modelAndView.addObject("venues", venueRepository.findAll(paginationBean.defaultPageable(pageNum - 1)));
            return modelAndView;
        } else {
            Query searchQuery = searchService.getQuery(query, city, category, reviews, speciality,
                    sortingType, paginationBean.defaultPageable(pageNum - 1));
            List<Venue> venues = mongoOperations.find(searchQuery, Venue.class);
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
            venues.getVenues().addAll(venueRepository.
                    findTop10ByNameLikeIgnoreCase(query));
        } else {
            venues.getVenues().addAll(venueRepository.
                    findTop10ByCityAndCategoryAndNameLikeIgnoreCase(city, category, query));
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
