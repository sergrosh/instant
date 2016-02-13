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
public interface CityRepository extends MongoRepository<CityRepository, String> {
    Page<City> findAllBy(TextCriteria textCriteria, Pageable pageable);
    public List<City> findCityByNameOrZip(String query);
    public List<City> findTop10();
    public List<City> findTop10ByName(String name);
}
