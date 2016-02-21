package com.instant.persistence.model.venue;


import com.instant.persistence.model.Product;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * @author sroshchupkin
 */

@Document
@ToString(exclude="id")
@EqualsAndHashCode(exclude="id")
public class Venue {

    @Id
    @Getter @Setter private String id;
    @TextIndexed(weight = 3)
    @Getter @Setter private String name;
    @Getter @Setter private String country;
    @Getter @Setter private String city;
    @Getter @Setter private String category;
    @Getter @Setter private String company;
    @Getter @Setter private String email;
    @Getter @Setter private String phonePrimary;
    @Getter @Setter private String phoneSecondary;
    @Getter @Setter private String mobilePrimary;
    @Getter @Setter private String mobileSecondary;
    @Getter @Setter private String facebookPage;
    @Getter @Setter private String website;
    @Getter @Setter private String address;
    @Getter @Setter private String imagePath;
    @Getter @Setter private String description;
    @Getter @Setter private List<String> speciality;
    @Getter @Setter private List<Product> menu;
    @Getter @Setter private List<String> gallery;
    @Getter @Setter private double rating;
    @Getter @Setter private int reviews;
    @Getter @Setter private int likes;
    @Getter @Setter private String specialities;
}
