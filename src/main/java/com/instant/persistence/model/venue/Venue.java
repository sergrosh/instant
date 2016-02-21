package com.instant.persistence.model.venue;


import com.instant.persistence.model.BaseModel;
import com.instant.persistence.model.Product;
import lombok.Data;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * @author sroshchupkin
 */

@Data
@Document
public class Venue extends BaseModel{
    
    @TextIndexed(weight = 3)
    private String name;
    private String country;
    private String city;
    private String category;
    private String company;
    private String email;
    private String phonePrimary;
    private String phoneSecondary;
    private String mobilePrimary;
    private String mobileSecondary;
    private String facebookPage;
    private String website;
    private String address;
    private String imagePath;
    private String description;
    private List<String> speciality;
    private List<Product> menu;
    private List<String> gallery;
    private double rating;
    private int reviews;
    private int likes;
    private String specialities;
}
