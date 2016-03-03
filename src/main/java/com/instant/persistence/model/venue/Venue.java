package com.instant.persistence.model.venue;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.instant.api.model.NewVenue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author sroshchupkin
 */

@ApiModel(description = "")
@Data
@Document
public class Venue extends NewVenue {

    @Id
    private String id;

    @JsonProperty("published")
    private Boolean published;

    @ApiModelProperty(required = false, value = "")
    @JsonProperty("rating")
    private double rating;

    @ApiModelProperty(required = false, value = "")
    @JsonProperty("reviews")
    private int reviews;

    @ApiModelProperty(required = false, value = "")
    @JsonProperty("likes")
    private int likes;

    @GeoSpatialIndexed
    @ApiModelProperty(value = "")
    @JsonProperty("location")
    private GeoJsonPoint location;
}
