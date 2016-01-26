package com.instant.persistence.repository;

import com.instant.persistence.model.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author sroshchupkin
 */
public interface RestaurantRepository extends MongoRepository<Restaurant, String> {
    List<Restaurant> findByName(String name);

    Restaurant findByAddress(String address);
}
