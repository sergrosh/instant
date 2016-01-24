package com.instant.controller;

import com.instant.persistence.model.Restaurant;
import com.instant.persistence.repository.RestaurantRepository;
import com.instant.validator.RestaurantValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @author sroshchupkin
 */

@Controller
public class RestaurantController {

    @Autowired
    RestaurantValidator restaurantValidator;

    @Autowired
    RestaurantRepository restaurantRepository;

    @RequestMapping(value = Mappings.RESTAURANT_CREATE_DO, method = RequestMethod.POST)
    public ModelAndView save(Restaurant restaurant) {
        Map<String, String> errorsMap=restaurantValidator.isValid(restaurant);
        if(errorsMap.isEmpty())
        {
            ModelAndView view = new ModelAndView("success");
            restaurantRepository.save(restaurant);
            return view;
        }
        else {
            ModelAndView view = new ModelAndView("fail");
            return view;
        }

    }





}
