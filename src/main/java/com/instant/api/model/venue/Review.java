package com.instant.api.model.venue;

import lombok.Data;

/**
 * @author sroshchupkin
 */

@Data
public class Review {
    private double rating;
    private String text;
    private String userId;
}
