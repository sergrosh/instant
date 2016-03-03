package com.instant.service.geo;

import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

/**
 * @author sroshchupkin
 */
public interface GeoCoderService {
    GeoJsonPoint getGeoPointFromAddress(String locationAddress);
}
