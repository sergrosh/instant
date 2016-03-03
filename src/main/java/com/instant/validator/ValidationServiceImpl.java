package com.instant.validator;

import com.instant.persistence.model.venue.Venue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author sroshchupkin
 */

@Slf4j
@Service
public class ValidationServiceImpl implements ValidationService<Venue> {

    @Autowired
    private Validator validator;

    @Override
    public ValidationResult validate(Venue venue) {

        List<String> errorMsgs = null;

        Set<ConstraintViolation<Venue>> violations = validator.validate(venue);

        if (!violations.isEmpty()) {
            errorMsgs = violations.stream()
                    .map(violation -> String.format("%s %s", violation.getPropertyPath(), violation.getMessage()))
                    .collect(Collectors.toList());
            log.debug("Validation failed {}", errorMsgs);
        }

        return new ValidationResult.Builder(errorMsgs).build();
    }
}
