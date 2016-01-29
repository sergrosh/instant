package com.instant.persistence.repository;

import com.instant.persistence.model.Venue;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author sroshchupkin
 */
public interface VenueRepository extends MongoRepository<Venue, String> {
    List<Venue> findByName(String name);

    Venue findByAddress(String address);

    List<Venue> findByType(String type);

    List<Venue> findByNameAndAddressAllIgnoreCase(String name, String address);

}