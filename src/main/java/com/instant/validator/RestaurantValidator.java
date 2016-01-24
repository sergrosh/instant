package com.instant.validator;

import com.instant.persistence.model.Restaurant;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author sroshchupkin
 */
public class RestaurantValidator {
    public Map<String, String> isValid(Restaurant restaurant) {

        Map<String, String> errors = new HashMap<>();

        if (StringUtils.isEmpty(restaurant.getName())) {
            errors.put("Restaurant title", "is required!");
        }

        if (StringUtils.isEmpty(restaurant.getAddress())) {
            errors.put("Restaurant address", "is required");
        }
        return errors;
    }
}
