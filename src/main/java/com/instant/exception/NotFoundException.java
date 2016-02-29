package com.instant.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author sroshchupkin
 */

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such element")
public class NotFoundException extends RuntimeException {
}
