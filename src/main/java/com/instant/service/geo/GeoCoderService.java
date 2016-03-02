package com.instant.service.geo;

import org.springframework.data.geo.Point;

/**
 * @author sroshchupkin
 */
public interface GeoCoderService {
    Point getGeoPointFromAddress(String locationAddress);
}
