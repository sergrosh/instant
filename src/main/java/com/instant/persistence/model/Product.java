package com.instant.persistence.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Model class of product
 *
 * @author sroshchupkin
 */
@Data
@Document
public class Product extends NamedModel{
    private String type;
    private double price;
    private String imagePath;
    private String description;
    private double rating;
    private int reviews;
    private int likes;
}
