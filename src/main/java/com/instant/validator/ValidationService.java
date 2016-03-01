package com.instant.validator;


/**
 * @author sroshchupkin
 */

public interface ValidationService <Venue>{
    ValidationResult validate(Venue model);
}
