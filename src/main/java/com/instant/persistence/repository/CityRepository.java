package com.instant.persistence.repository;

import com.instant.persistence.model.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author sroshchupkin
 */
public interface CityRepository extends MongoRepository<City, String> {
    Page<City> findAllBy(TextCriteria textCriteria, Pageable pageable);

    List<City> findCityByName(String cityName);

    List<City> findTop10ByNameLikeIgnoreCase(String name);
}
