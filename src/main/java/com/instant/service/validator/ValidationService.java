package com.instant.service.validator;


/**
 * @author sroshchupkin
 */

public interface ValidationService<NewVenue> {
    ValidationResult validate(NewVenue model);
}
