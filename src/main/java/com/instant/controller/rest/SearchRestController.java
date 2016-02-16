package com.instant.controller.rest;

import com.instant.common.PaginationBean;
import com.instant.controller.Mappings;
import com.instant.persistence.model.venue.Venue;
import com.instant.persistence.repository.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author sroshchupkin
 */

@RestController
public class SearchRestController {

    @Autowired
    VenueRepository venueRepository;

    @Autowired
    private PaginationBean paginationBean;

    @RequestMapping(Mappings.REST_CLIENTS)
    public Page<Venue> search(@RequestParam("query") String query, @RequestParam("category") String category,
                              @RequestParam(value = "view", required = false, defaultValue = "1") Integer view,
                              @RequestParam(value = "page", required = false, defaultValue = "1") Integer pageNum,
                              @RequestParam(value = "option1", required = false, defaultValue = "1") String opt1,
                              @RequestParam(value = "option2", required = false, defaultValue = "1") String opt2,
                              @RequestParam(value = "option3", required = false, defaultValue = "1") String opt3,
                              @RequestParam(value = "option4", required = false, defaultValue = "1") String opt4) {
        if (StringUtils.isEmpty(query)) {
            return venueRepository.findAll(paginationBean.defaultPageable(pageNum - 1));
        } else {
            TextCriteria criteria = TextCriteria.forDefaultLanguage().matchingPhrase(query);
            return venueRepository.findAllBy(criteria, paginationBean.defaultPageable(pageNum - 1));
        }
    }


    @RequestMapping(path = Mappings.REST_SUGGESTIONS, method = RequestMethod.GET)
    public List<Venue> getVenueNameSuggestions(@RequestParam(value = "query") String query,
                                               @RequestParam(value = "city", required = false, defaultValue = "Berlin") String city,
                                               @RequestParam(value = "category", required = false, defaultValue = "ALL") String category) {
        if (category.toUpperCase().equals("ALL") && city.equals("Berlin")) {
            return venueRepository.findTop10ByNameLikeIgnoreCase(query);
        } else {
            return venueRepository.findTop10ByCityAndCategoryAndNameLikeIgnoreCase(city, category, query);
        }
    }

}
