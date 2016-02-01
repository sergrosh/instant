package com.instant.controller;

import com.instant.common.PaginationBean;
import com.instant.persistence.repository.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public ModelAndView search(@RequestParam("query") String query,
                               @RequestParam(value = "page", required = false, defaultValue = "1") Integer pageNum) {
        ModelAndView modelAndView = new ModelAndView(TilesDefinition.HOME);
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
}
