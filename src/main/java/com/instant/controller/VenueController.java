package com.instant.controller;

import com.instant.api.model.venue.NewVenue;
import com.instant.api.model.venue.Venue;
import com.instant.persistence.model.city.City;
import com.instant.persistence.model.venue.VenueEntity;
import com.instant.persistence.repository.CityRepository;
import com.instant.persistence.repository.UploadedFileRepository;
import com.instant.persistence.repository.VenueRepository;
import com.instant.service.validator.VenueValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.support.ConfigurableConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Autowired
    private ConfigurableConversionService conversionService;

    @Autowired
    UploadedFileRepository uploadedFileRepository;

    private static final String MAP = "map";
    private static final String MENU = "menu";
    private static final String CONTACT = "contact";
    private static final String REVIEWS = "reviews";

    @RequestMapping(Mappings.ITEM + "/{id}")
    public ModelAndView getItemByIdViaUrl(@PathVariable(value = "id") String id) {
        ModelAndView modelAndView;
        if (StringUtils.isEmpty(id)) {
            modelAndView = new ModelAndView(TilesDefinition.HOME);
            return modelAndView;
        } else {
            modelAndView = new ModelAndView(TilesDefinition.ITEM);
            modelAndView.addObject("venue", conversionService.convert(venueRepository.findById(id), Venue.class));
            modelAndView.addObject("view", "item_main_view");
        }
        return modelAndView;
    }

    @RequestMapping("/new_item/{id}")
    public ModelAndView getNewItemByIdViaUrl(@PathVariable(value = "id") String id) {
        ModelAndView modelAndView;
        if (StringUtils.isEmpty(id)) {
            modelAndView = new ModelAndView(TilesDefinition.HOME);
            return modelAndView;
        } else {
            modelAndView = new ModelAndView("index_" + TilesDefinition.ITEM);
            modelAndView.addObject("venue", conversionService.convert(venueRepository.findById(id), Venue.class));
            modelAndView.addObject("view", "item_main_view");
        }
        return modelAndView;
    }

    @RequestMapping(Mappings.ITEM + "/{id}/{block}")
    public ModelAndView getItemBlockByIdViaUrl(@PathVariable(value = "id") String id, @PathVariable(value = "block") String block) {
        ModelAndView modelAndView;
        if (StringUtils.isEmpty(id)) {
            modelAndView = new ModelAndView(TilesDefinition.HOME);
            return modelAndView;
        } else {
            modelAndView = new ModelAndView(TilesDefinition.ITEM);
            modelAndView.addObject("venue", conversionService.convert(venueRepository.findById(id),Venue.class));
            switch (block.toLowerCase()) {
                case MAP:
                    modelAndView.addObject("view", "item_map_view");
                    break;
                case MENU:
                    modelAndView.addObject("view", "item_menu_view");
                    break;
                case CONTACT:
                    modelAndView.addObject("view", "item_contact_view");
                    break;
                case REVIEWS:
                    modelAndView.addObject("view", "item_reviews_view");
                    break;
                default:
                    modelAndView.addObject("view", "item_main_view");
                    break;
            }

        }
        return modelAndView;
    }

    @RequestMapping(value = Mappings.VENUE_SAVE, method = RequestMethod.POST)
    public ModelAndView save(NewVenue newVenue) {
        VenueEntity venue = conversionService.convert(newVenue,VenueEntity.class);
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

//    @RequestMapping(value = Mappings.ITEM_FAVOURITE, method = RequestMethod.GET)
//    public Venue addToFavourites(@RequestParam("id") String id){
//
//
//    }

}
