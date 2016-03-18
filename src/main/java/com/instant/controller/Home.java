package com.instant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author sroshchupkin
 */

@Controller
public class Home {

    @RequestMapping("/")
    public ModelAndView index() {
        return new ModelAndView("redirect:/home");
    }

    @RequestMapping(Mappings.HOME)
    public String reindex() {
        return TilesDefinition.LANDING;
    }

}
