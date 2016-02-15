package com.instant.controller;

import com.instant.persistence.model.City;
import com.instant.persistence.model.Venue;
import com.instant.persistence.repository.CityRepository;
import com.instant.persistence.repository.VenueRepository;
import com.instant.validator.VenueValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @author sroshchupkin
 */

@Controller
public class VenueController {

    @Autowired
    VenueValidator venueValidator;

    @Autowired
    VenueRepository venueRepository;

    @Autowired
    CityRepository cityRepository;

    @RequestMapping(Mappings.ITEM)
    public ModelAndView getItemById(@RequestParam("id") String id) {
        ModelAndView modelAndView;
        if (StringUtils.isEmpty(id)) {
            modelAndView = new ModelAndView(TilesDefinition.HOME);
            return modelAndView;
        } else {
            modelAndView = new ModelAndView(TilesDefinition.ITEM);
            modelAndView.addObject("venue", venueRepository.findById(id));
        }
        return modelAndView;
    }

    @RequestMapping(value = Mappings.VENUE_SAVE, method = RequestMethod.POST)
    public ModelAndView save(Venue venue) {
        Map<String, String> errorsMap = venueValidator.isValid(venue);
        if (errorsMap.isEmpty()) {
            ModelAndView view = new ModelAndView(TilesDefinition.LANDING);
            venueRepository.save(venue);
            if (cityRepository.findCityByName(venue.getCity()).isEmpty()) {
                City newCity = new City();
                newCity.setName(venue.getCity());
                cityRepository.save(newCity);
            }
            return view;
        } else {
            ModelAndView view = new ModelAndView(TilesDefinition.LANDING);
            return view;
        }
    }

}
