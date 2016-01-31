package com.instant.controller;

import com.instant.persistence.model.Venue;
import com.instant.persistence.repository.VenueRepository;
import com.instant.validator.VenueValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @RequestMapping(value = Mappings.VENUE_SAVE, method = RequestMethod.POST)
    public ModelAndView save(Venue venue) {
//        Map<String, String> errorsMap = venueValidator.isValid(venue);
//        if (errorsMap.isEmpty()) {
            ModelAndView view = new ModelAndView("index");
            venueRepository.save(venue);
//            venueRepository.findByName("name");
            return view;
//        } else {
//            ModelAndView view = new ModelAndView("index");
//            return view;
//        }

    }

//    @RequestMapping(value = Mappings.VENUE_SAVE, method = RequestMethod.POST)
//    public ModelAndView saveVenue(@RequestBody Venue venue) {
//        Map<String, String> errorsMap = venueValidator.isValid(venue);
//        if (errorsMap.isEmpty()) {
//            ModelAndView view = new ModelAndView("index");
//            venueRepository.save(venue);
//            venueRepository.findByName("name");
//            return view;
//        } else {
//            ModelAndView view = new ModelAndView("index");
//            return view;
//        }
//
//    }


}
