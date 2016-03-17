package com.instant.controller;

import com.instant.api.model.venue.NewVenue;
import com.instant.api.model.venue.Review;
import com.instant.api.model.venue.Venue;
import com.instant.persistence.model.city.City;
import com.instant.persistence.model.venue.VenueEntity;
import com.instant.persistence.repository.CityRepository;
import com.instant.persistence.repository.VenueRepository;
import com.instant.service.clickouts.ClickoutsUpdateService;
import com.instant.service.user.UserAccountService;
import com.instant.service.validator.VenueValidator;
import com.instant.service.venue.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.support.ConfigurableConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @author sroshchupkin
 */

@Controller
@RequestMapping(Mappings.VENUE)
public class VenueController {

    private static final String MAP = "map";
    private static final String MENU = "menu";
    private static final String CONTACT = "contact";
    private static final String REVIEWS = "reviews";
    private static final String VIEW = "view";

    @Autowired
    VenueService venueService;

    @Autowired
    VenueValidator venueValidator;
    @Autowired
    VenueRepository venueRepository;
    @Autowired
    CityRepository cityRepository;
    @Autowired
    private ConfigurableConversionService conversionService;
    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private ClickoutsUpdateService clickoutsUpdateService;


    @RequestMapping(Mappings.ID)
    public ModelAndView getNewItemByIdViaUrl(@PathVariable(value = "id") String id) {
        ModelAndView modelAndView;
        if (StringUtils.isEmpty(id)) {
            modelAndView = new ModelAndView(TilesDefinition.HOME);
            return modelAndView;
        } else {
            modelAndView = new ModelAndView("index_" + TilesDefinition.VENUE);
            modelAndView.addObject("venue", venueService.findVenueById(id));
            modelAndView.addObject("view", "item_main_view");
        }
        clickoutsUpdateService.updateClickoutMap(id);
        return modelAndView;
    }

    @RequestMapping(Mappings.ID+"/add_review")
    public ModelAndView addReview(@PathVariable("id") String id, @RequestBody Review review){
        Venue venue = venueService.findVenueById(id);
        venue.addReview(review);
        venueService.updateVenue(venue);
        ModelAndView modelAndView=new ModelAndView("index_" + TilesDefinition.VENUE);
        modelAndView.addObject("venue", venue);
        modelAndView.addObject("view", "item_main_view");
        return modelAndView;
    }

    @RequestMapping(Mappings.ID+"/{block}")
    public ModelAndView getItemBlockByIdViaUrl(@PathVariable(value = "id") String id, @PathVariable(value = "block") String block) {
        ModelAndView modelAndView;
        if (StringUtils.isEmpty(id)) {
            modelAndView = new ModelAndView(TilesDefinition.HOME);
            return modelAndView;
        } else {
            modelAndView = new ModelAndView(TilesDefinition.VENUE);
            modelAndView.addObject("venue", conversionService.convert(venueRepository.findById(id), Venue.class));
            switch (block.toLowerCase()) {
                case MAP:
                    modelAndView.addObject(VIEW, "item_map_view");
                    break;
                case MENU:
                    modelAndView.addObject(VIEW, "item_menu_view");
                    break;
                case CONTACT:
                    modelAndView.addObject(VIEW, "item_contact_view");
                    break;
                case REVIEWS:
                    modelAndView.addObject(VIEW, "item_reviews_view");
                    break;
                default:
                    modelAndView.addObject(VIEW, "item_main_view");
                    break;
            }

        }
        return modelAndView;
    }

    @RequestMapping(value = Mappings.SAVE, method = RequestMethod.POST)
    public ModelAndView save(NewVenue newVenue) {
        VenueEntity venue = conversionService.convert(newVenue, VenueEntity.class);
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
            return new ModelAndView(TilesDefinition.LANDING);
        }
    }

    @RequestMapping(value = Mappings.FAVOURITE, method = RequestMethod.GET)
    public boolean addToFavourites(@PathVariable("id") String id) {
        return userAccountService.getCurrentUser().addFavouriteVenue(id);
    }
}
