package com.instant.controller;

import com.instant.common.PaginationBean;
import com.instant.persistence.model.Venues;
import com.instant.persistence.repository.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author sroshchupkin
 */

@Controller
public class SearchController {

    @Autowired
    VenueRepository venueRepository;

    @Autowired
    private PaginationBean paginationBean;

    @RequestMapping(Mappings.CLIENTS)
    public ModelAndView search(@RequestParam("query") String query, @RequestParam("category") String category,
                               @RequestParam(value = "view", required = false, defaultValue = "1") Integer view,
                               @RequestParam(value = "page", required = false, defaultValue = "1") Integer pageNum,
                               @RequestParam(value = "option1", required = false, defaultValue = "1") String opt1,
                               @RequestParam(value = "option2", required = false, defaultValue = "1") String opt2,
                               @RequestParam(value = "option3", required = false, defaultValue = "1") String opt3,
                               @RequestParam(value = "option4", required = false, defaultValue = "1") String opt4) {
        ModelAndView modelAndView = new ModelAndView(TilesDefinition.HOME);

        if (view == 2) {
            modelAndView = new ModelAndView("fragments/itemlist_product_list :: itemlist_product_list");
        }

        if (StringUtils.isEmpty(query)) {
            modelAndView.addObject("venues", venueRepository.findAll(paginationBean.defaultPageable(pageNum - 1)));
            return modelAndView;
        } else {
            TextCriteria criteria = TextCriteria.forDefaultLanguage().matchingPhrase(query);
            modelAndView.addObject("venues", venueRepository.findAllBy(criteria, paginationBean.defaultPageable(pageNum - 1)));
            modelAndView.addObject("searchString", query);
        }

        return modelAndView;
    }


    @RequestMapping(path = Mappings.SUGGESTIONS, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Venues getVenueNameSuggestions(@RequestParam("query") String query) {
       Venues venues = new Venues();
        venues.getVenues().addAll(venueRepository.findTop10ByNameLikeIgnoreCase(query));
        return venues;
    }
}
