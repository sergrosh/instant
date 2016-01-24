package com.instant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author sroshchupkin
 */
@Controller
public class Home {

    @RequestMapping("/")
    public String index() {
        return "landing";
    }
}
