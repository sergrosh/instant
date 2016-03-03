package com.instant.persistence.model.item;

import com.instant.persistence.model.NamedModel;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Model class of product
 *
 * @author sroshchupkin
 */
@Data
@Document
public class Product extends NamedModel {
    private String type;
    private double price;
    private String imagePath;
    private String description;
    private double rating;
    private int reviews;
    private int likes;
}
