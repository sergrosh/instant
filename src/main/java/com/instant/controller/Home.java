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

    @RequestMapping("/home")
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


}
