package com.instant.validator;

import com.instant.persistence.model.Venue;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author sroshchupkin
 */

@Component
public class VenueValidator {
    public Map<String, String> isValid(Venue venue) {

        Map<String, String> errors = new HashMap<>();

        if (StringUtils.isEmpty(venue.getName())) {
            errors.put("Venue title", "is required!");
        }

        if (StringUtils.isEmpty(venue.getAddress())) {
            errors.put("Venue address", "is required");
        }
        return errors;
    }
}
