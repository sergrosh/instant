package com.instant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author sroshchupkin
 */
@Controller
public class Home {

    @RequestMapping("/")
    public String index() {
        return "landing";
    }

    @RequestMapping("/clients")
    public String getClients(@RequestParam("query") String query, Model model) {
        return "index";
    }

    @RequestMapping("/item")
    public String getClients(Model model) {
        return "item";
    }

    @RequestMapping("/account")
    public String getAccount(String speciality, Model model) {
        return "account";
    }

    @RequestMapping("/user")
    public String getUser(String speciality, Model model) {
        model.addAttribute("accounts_section","accounts_dashboard");
        return "user";
    }

    @RequestMapping("/user/add")
    public String getUserAddVenuFragment(String speciality, Model model) {
        model.addAttribute("accounts_section","accounts_add_venue");
        return "user";
    }




}
