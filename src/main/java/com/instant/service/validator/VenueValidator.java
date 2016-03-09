package com.instant.service.validator;

import com.instant.persistence.model.venue.VenueEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author sroshchupkin
 */

@Component
public class VenueValidator {
    public Map<String, String> isValid(VenueEntity venueEntity) {

        Map<String, String> errors = new HashMap<>();

        if (StringUtils.isEmpty(venueEntity.getName())) {
            errors.put("Venue title", "is required!");
        }

        if (StringUtils.isEmpty(venueEntity.getCity())) {
            errors.put("City name", "is required");
        }

        if (StringUtils.isEmpty(venueEntity.getCompany())) {
            errors.put("Venue company name", "is required");
        }

        if (StringUtils.isEmpty(venueEntity.getDescription())) {
            errors.put("Venue description", "is required");
        }
        return errors;
    }
}
