package com.instant.persistence.model.item;

import com.instant.persistence.model.NamedModel;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author sroshchupkin
 */

@Data
@Document
public class Dish extends NamedModel {
    private double price;
    private String description;
    private String imagePath;
}
