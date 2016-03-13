package com.instant.persistence.model.venue;

import com.instant.api.model.venue.Review;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * @author sroshchupkin
 */

@Data
@Document(collection = "venue")
public class VenueEntity {

    @Id
    private String id;

    private String name;
    private String country;
    private String city;
    private String description;
    private String address;
    private String category;
    private String company;

    private String email;
    private String phonePrimary;
    private String phoneSecondary;
    private String mobilePrimary;
    private String mobileSecondary;

    private String whatsApp;
    private String facebookPage;
    private String website;

    private String imagePath;

    private List<String> speciality;
    private List<String> menu;
    private Double avgPrice;
    private List<String> gallery;

//    private LocalTime[] openTimes;
//    private LocalTime[] closeTimes;

    private String openHours;

    @GeoSpatialIndexed
    private double[] location;

    private Boolean published;
    private int clickouts;
    private double rating;
    private List<Review> reviews;
    private int likes;

    //Additional
    private String parkingDetails;
    private Boolean wifiAvailability;
    private Boolean smokingAvailability;
    private Boolean accessForDisabled;
    private Boolean takeAwayAvailability;
    private String outdoorEnvironment;
    private String kidsPlayground;
}
