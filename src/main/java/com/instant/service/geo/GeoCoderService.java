package com.instant.service.geo;


/**
 * @author sroshchupkin
 */
public interface GeoCoderService {
    double[] getLocationFromAddress(String locationAddress);
}
