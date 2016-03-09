package com.instant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author sroshchupkin
 */

@Controller
public class Home {

    @RequestMapping("/")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("redirect:/home");
        return modelAndView;
    }

    @RequestMapping(Mappings.HOME)
    public String reindex() {
        return TilesDefinition.LANDING;
    }

    @RequestMapping(Mappings.ACCOUNT)
    public String getAccount(String speciality, Model model) {
        return TilesDefinition.ACCOUNT;
    }

    @RequestMapping(Mappings.USER)
    public String getUser(String speciality, Model model) {
        model.addAttribute("accounts_section", "accounts_dashboard");
        return "index_user";
    }

    @RequestMapping(Mappings.USER_ADD)
    public String getUserAddVenuFragment(String speciality, Model model) {
        model.addAttribute("accounts_section", "accounts_add_venue");
        return TilesDefinition.USER;
    }

    @RequestMapping("/user/add_new")
    public String getNewUserAddVenuFragment(String speciality, Model model) {
        model.addAttribute("register_venue_page", "index_user_add_first_page");
        return "index_user_add";
    }

    @RequestMapping("/user/add_new/cont")
    public String getNewUserAddVenuFragmentContinued(String speciality, Model model) {
        model.addAttribute("register_venue_page", "index_user_add_second_page");
        return "index_user_add";
    }


}
