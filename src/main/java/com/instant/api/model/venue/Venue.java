package com.instant.api.model.venue;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author sroshchupkin
 */

@ApiModel(description = "")
@Data
public class Venue extends NewVenue {
    @ApiModelProperty(required = true, value = "")
    @JsonProperty("id")
    private String id;

    @ApiModelProperty(required = false, value = "")
    @JsonProperty("location")
    private double[] location;

    @ApiModelProperty(required = false, value = "")
    @JsonProperty("favourite")
    private boolean favourite;

    @ApiModelProperty(required = false, value = "")
    @JsonProperty("rating")
    private double rating;

    @ApiModelProperty(required = false, value = "")
    @JsonProperty("reviews")
    private List<Review> reviews;

    @ApiModelProperty(required = false, value = "")
    @JsonProperty("likes")
    private int likes;
}
