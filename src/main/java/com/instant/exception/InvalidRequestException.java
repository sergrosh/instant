package com.instant.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author sroshchupkin
 */

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "No such element")
public class InvalidRequestException extends RuntimeException {
}
