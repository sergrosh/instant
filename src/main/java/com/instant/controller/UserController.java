package com.instant.controller;

import com.instant.api.model.venue.NewVenue;
import com.instant.api.model.venue.Venue;
import com.instant.persistence.model.social.UserAccount;
import com.instant.service.user.UserAccountService;
import com.instant.service.venue.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author sroshchupkin
 */

@RequestMapping(Mappings.USER)
@Controller
public class UserController {

    @Autowired
    VenueService venueService;

    @Autowired
    UserAccountService userAccountService;

    private static final String ADD_NEW = "/add_new";
    private static final String EDIT_VENUE = "/edit/venue";
    private static final String REGISTER_VENUE = "register_venue_page";

    @RequestMapping
    public String getUser(Model model) {
        model.addAttribute("accounts_section", "accounts_dashboard");
        model.addAttribute("user", userAccountService.getCurrentUser());
        return "index_user";
    }

    @RequestMapping(value = ADD_NEW, method = RequestMethod.GET)
    public String getNewUserAddVenuFragment(Model model) {
        ModelAndView view = new ModelAndView(TilesDefinition.LANDING);
        model.addAttribute(REGISTER_VENUE, "index_user_add_first_page");
        return "index_user_add";
    }

    @ResponseBody
    @RequestMapping(value = ADD_NEW, method = RequestMethod.POST)
    public Venue getNewUserAddVenuFragment(@RequestBody NewVenue newVenue) {
        Venue venue = venueService.saveVenue(newVenue);
        UserAccount currentUser = userAccountService.getCurrentUser();
        currentUser.addMyVenue(venue.getId());
        userAccountService.updateUserAccount(currentUser);
        return venue;
    }

    @RequestMapping(value = ADD_NEW + Mappings.ID, method = RequestMethod.GET)
    public String getNewUserAddVenuFragmentContinued(@PathVariable("id") String id, Model model) {
        if (!userAccountService.getCurrentUser().getMyVenues().contains(id))
            return Mappings.ERROR_PATH;
        model.addAttribute(REGISTER_VENUE, "index_user_add_second_page");
        return "index_user_add";
    }

    @ResponseBody
    @RequestMapping(value = ADD_NEW + Mappings.ID, method = RequestMethod.POST)
    public Venue getNewUserAddVenuFragmentContinued(@RequestBody Venue venue) {
        Venue updatedVenue = venueService.updateVenue(venue);
        return updatedVenue;
    }

    @RequestMapping(EDIT_VENUE + Mappings.ID)
    public String editVenue(@PathVariable("id") String id, Model model) {
        Venue venue = venueService.findVenueById(id);
        model.addAttribute("venue", venue);
        model.addAttribute(REGISTER_VENUE, "index_user_add_second_page");
        return "index_user_edit";
    }

    @ResponseBody
    @RequestMapping(value = EDIT_VENUE + Mappings.ID, method = RequestMethod.POST)
    public Venue editVenue(@RequestBody Venue venue) {
        Venue updatedVenue = venueService.saveVenue(venue);
        return updatedVenue;
    }


//    @RequestMapping("/user/edit")
//    public String editUser(String speciality, Model model) {
//        model.addAttribute("register_venue_page", "index_user_add_second_page");
//        return "index_user_edit";
//    }


}
