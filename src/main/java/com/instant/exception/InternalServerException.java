package com.instant.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author sroshchupkin
 */

@ResponseStatus(value= HttpStatus.INTERNAL_SERVER_ERROR, reason="Internal Error")
public class InternalServerException extends RuntimeException {
}
