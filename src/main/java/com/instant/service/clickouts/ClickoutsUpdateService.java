package com.instant.service.clickouts;

/**
 * @author sroshchupkin
 */
public interface ClickoutsUpdateService {
    int getClickoutMapSize();
    void updateClickoutMap(String venueId);
    void updateEntity();
}
