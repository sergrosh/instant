package com.instant.controller;

import com.instant.api.model.venue.NewVenue;
import com.instant.api.model.venue.Venue;
import com.instant.service.venue.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author sroshchupkin
 */

@Controller
public class UserController {

    @Autowired
    VenueService venueService;

    @RequestMapping(Mappings.USER)
    public String getUser(String speciality, Model model) {
        model.addAttribute("accounts_section", "accounts_dashboard");
        return "index_user";
    }

    @RequestMapping("/add")
    public String getUserAddVenuFragment(String speciality, Model model) {
        model.addAttribute("accounts_section", "accounts_add_venue");
        return TilesDefinition.USER;
    }

    @RequestMapping(value = "user/add_new", method = RequestMethod.GET)
    public String getNewUserAddVenuFragment(Model model) {
        ModelAndView view = new ModelAndView(TilesDefinition.LANDING);
        model.addAttribute("register_venue_page", "index_user_add_first_page");
        return "index_user_add";
    }

    @RequestMapping(value = "user/add_new", method = RequestMethod.POST)
    public Venue getNewUserAddVenuFragment(NewVenue newVenue) {
        ModelAndView view = new ModelAndView(TilesDefinition.LANDING);
        Venue venue = venueService.saveVenue(newVenue);
        return venue;
    }

    @RequestMapping("/user/add_new/{id}")
    public String getNewUserAddVenuFragmentContinued(String speciality, Model model) {
        model.addAttribute("register_venue_page", "index_user_add_second_page");
        return "index_user_add";
    }

    @RequestMapping("/user/edit")
    public String editVenue(String speciality, Model model) {
        model.addAttribute("register_venue_page", "index_user_add_second_page");
        return "index_user_edit";
    }

    @RequestMapping("/user/add_new/terms")
    public String getNewUserAddVenuFragmentTerms(String speciality, Model model) {
        model.addAttribute("register_venue_page", "index_user_add_third_page");
        return "index_user_add";
    }

}
