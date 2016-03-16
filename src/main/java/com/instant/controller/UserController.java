package com.instant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author sroshchupkin
 */

@Controller
public class UserController {
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

    @RequestMapping("/user/add_new/terms")
    public String getNewUserAddVenuFragmentTerms(String speciality, Model model) {
        model.addAttribute("register_venue_page", "index_user_add_third_page");
        return "index_user_add";
    }

}
