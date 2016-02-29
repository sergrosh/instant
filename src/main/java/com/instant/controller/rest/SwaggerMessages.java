package com.instant.controller.rest;

/**
 * Swagger error messages
 *
 * @author sroshchupkin
 */
public final class SwaggerMessages {

    public static final String SUCCESS = "success";
    public static final String ERROR_400 = "Bad request. If request not parsable, or contains not valid parameters. Also all related to business logic errors.";
    public static final String ERROR_500_MSG = "Internal server error. Connection to data storage failed, etc. No errors related to business logic.";
    public static final String ERROR_404_MSG = "venue not found. If id invalid return '400' error code.";

    private SwaggerMessages() {
    }
}
