package com.instant.controller.rest.venue;

import com.instant.controller.Mappings;
import com.instant.persistence.model.venue.Venue;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.instant.controller.rest.SwaggerMessages.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Venue rest controller - GET, POST, PUT, DELETE for CRUD operation for venues
 *
 * @author sroshchupkin
 */

@Api(value = Mappings.REST_VENUE, description = "API to manage venues")
public interface VenueRestController {

    /**
     * GET ALL VENUES
     * @param limit
     * @param offset
     * @return
     */
    @ApiOperation(
            value = "",
            notes = "Return a set of venues from the system regarding to 'limit' and 'offset' parameters. If nothing found return an empty set.",
            response = Venue.class,
            responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = SUCCESS),
            @ApiResponse(code = 400, message = ERROR_400),
            @ApiResponse(code = 500, message = ERROR_500_MSG)})
    @RequestMapping(method = RequestMethod.GET, produces = {APPLICATION_JSON_VALUE})
    List<Venue> getVenues(
            @ApiParam(value = "maximum number of results to return. Default is 20. Limit should be less then 1000.") @RequestParam(value = "limit",
                    required = false,
                    defaultValue = "20") Integer limit,

            @ApiParam(value = "This parameter specify where to start returning venues from the entire set of result. Default is 0.") @RequestParam(value = "offset",
                    required = false,
                    defaultValue = "0") Integer offset);


    /**
     * CREATE-POST new Venue
     * @param venue
     * @return
     */
    @ApiOperation(value = "", notes = "Creates a new venue. Duplicates are allowed.", response = Venue.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = SUCCESS),
            @ApiResponse(code = 400, message = ERROR_400),
            @ApiResponse(code = 500, message = ERROR_500_MSG)})


    @RequestMapping(method = RequestMethod.POST, produces = {APPLICATION_JSON_VALUE}, consumes = {APPLICATION_JSON_VALUE})
    Venue createVenue(@ApiParam(value = "Venue to add to the system",
            required = true) @RequestBody Venue venue);

    /**
     * GET VENUE
     * @param id
     * @return
     */
    @ApiOperation(value = "", notes = "Return a venue based on a single id.", response = Venue.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = SUCCESS),
            @ApiResponse(code = 400, message = ERROR_400),
            @ApiResponse(code = 404, message = ERROR_404_MSG),
            @ApiResponse(code = 500, message = ERROR_500_MSG)})

    @RequestMapping(value = "/{id}", produces = {APPLICATION_JSON_VALUE}, method = RequestMethod.GET)
    Venue getVenue(@ApiParam(value = "id of venue to fetch", required = true) @PathVariable("id") String id);

    /**
     * UPDATE VENUE
     * @param venue
     * @return
     */
    @ApiOperation(value = "", notes = "Update venue", response = Venue.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = SUCCESS),
            @ApiResponse(code = 400, message = ERROR_400),
            @ApiResponse(code = 404, message = ERROR_404_MSG),
            @ApiResponse(code = 500, message = ERROR_500_MSG)})

    @RequestMapping(value = "/{id}",
            produces = {APPLICATION_JSON_VALUE},
            consumes = {APPLICATION_JSON_VALUE},
            method = RequestMethod.PUT)
    Venue updateVenue(@ApiParam(value = "venue that will be updated", required = true) @RequestBody Venue venue);

    /**
     * DELETE VENUE
     * @param id
     */
    @ApiOperation(value = "", notes = "delete a single venue based on the id supplied", response = Venue.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = SUCCESS),
            @ApiResponse(code = 400, message = ERROR_400),
            @ApiResponse(code = 404, message = ERROR_404_MSG),
            @ApiResponse(code = 500, message = ERROR_500_MSG)})

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}", produces = {APPLICATION_JSON_VALUE}) void deleteVenue(@ApiParam(value = "id of venue to delete",
            required = true) @PathVariable("id") String id

    );

}
