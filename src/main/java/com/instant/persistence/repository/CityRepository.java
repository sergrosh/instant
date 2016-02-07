package com.instant.persistence.repository;

import com.instant.persistence.model.City;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author sroshchupkin
 */
public interface CityRepository extends MongoRepository<CityRepository, String> {

    public List<City> findCityByNameOrZip(String query);
}
