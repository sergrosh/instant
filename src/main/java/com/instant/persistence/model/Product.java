package com.instant.persistence.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Model class of product
 *
 * @author sroshchupkin
 */
@ToString(exclude="id")
@EqualsAndHashCode(exclude="id")
public class Product {
    @Getter @Setter private String name;
    @Getter @Setter private String type;
    @Getter @Setter private double price;
    @Getter @Setter private String imagePath;
    @Getter @Setter private String description;
    @Getter @Setter private double rating;
    @Getter @Setter private int reviews;
    @Getter @Setter private int likes;
}
