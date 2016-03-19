package com.instant.api.model.venue;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.ArrayList;
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

    @JsonProperty("user")
    @NotEmpty
    private String userId;

    @ApiModelProperty(required = false, value = "")
    @JsonProperty("location")
    private double[] location;

    @ApiModelProperty(required = false, value = "")
    @JsonProperty("menu")
    private List<String> menu;

    @ApiModelProperty(required = false, value = "")
    @JsonProperty("gallery")
    private List<String> gallery;

    @ApiModelProperty(required = false, value = "")
    @JsonProperty("favourite")
    private boolean favourite;

    @ApiModelProperty(required = false, value = "")
    @JsonProperty("reviewed")
    private boolean reviewed;

    @ApiModelProperty(required = false, value = "")
    @JsonProperty("rating")
    private double rating;

    @ApiModelProperty(required = false, value = "")
    @JsonProperty("reviews")
    private List<Review> reviews;

    @ApiModelProperty(required = false, value = "")
    @JsonProperty("likes")
    private int likes;

    public List<String> getMenu(){
        if(menu==null)
            menu=new ArrayList<>();
        return menu;
    }

    public List<String> getGallery(){
        if(gallery==null)
            gallery=new ArrayList<>();
        return gallery;
    }

    public List<Review> getReviews(){
        if(reviews==null)
            reviews=new ArrayList<>();
        return reviews;
    }

    public void addDish(String newDish) {
        List<String> menu = getMenu();
        menu.add(newDish);
    }

    public void addImage(String imageId) {
        List<String> gallery = getGallery();
        gallery.add(imageId);
    }

    public void addReview(Review review) {
        List<Review> reviews = getReviews();
        reviews.add(review);
    }


}
