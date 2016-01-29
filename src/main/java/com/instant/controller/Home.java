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

    @RequestMapping("/account")
    public String getAccount(String speciality, Model model) {
        return "account";
    }

    @RequestMapping("/user")
    public String getUser(String speciality, Model model) {
        return "user";
    }


}
