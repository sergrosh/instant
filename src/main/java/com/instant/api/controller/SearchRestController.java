package com.instant.api.controller;

import com.instant.api.model.venue.Venue;
import com.instant.controller.Mappings;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static com.instant.controller.rest.SwaggerMessages.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * @author sroshchupkin
 */

@Api(value = Mappings.REST + Mappings.SEARCH, description = "API to search")
public interface SearchRestController {

    @ApiOperation(
            value = "",
            notes = "Return a set of venues from the system regarding to search query and sort options",
            response = Venue.class,
            responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = SUCCESS),
            @ApiResponse(code = 400, message = ERROR_400),
            @ApiResponse(code = 500, message = ERROR_500_MSG)})
    @RequestMapping(method = RequestMethod.GET, produces = {APPLICATION_JSON_VALUE})
    List<Venue> search(

            @ApiParam(value = "search query e.g. venue's name or tag")
            @RequestParam(value = "query", required = false) String query,

            @ApiParam(value = "category- restaurant, bar, etc.")
            @RequestParam(value = "category", required = false, defaultValue = "") String category,

            @ApiParam(value = "page number")
            @RequestParam(value = "page", required = false, defaultValue = "1") Integer pageNum,

            @ApiParam(value = "city name")
            @RequestParam(value = "city", required = false, defaultValue = "Berlin") String city,

            @ApiParam(value = "reviews")
            @RequestParam(value = "reviews", required = false, defaultValue = "0") int reviews,

            @ApiParam(value = "speciality")
            @RequestParam(value = "speciality", required = false, defaultValue = "") List<String> speciality,

            @ApiParam(value = "sorting type")
            @RequestParam(value = "sortingType", required = false, defaultValue = "") String sortingType);
}
