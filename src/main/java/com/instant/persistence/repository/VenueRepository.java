package com.instant.persistence.repository;

import com.instant.persistence.model.Venue;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

/**
 * @author sroshchupkin
 */
public interface VenueRepository extends MongoRepository<Venue, String> {

    List<Venue> findAllBy(TextCriteria textCriteria, Pageable pageable);

    List<Venue> findAllByOrderByNameDesc(TextCriteria criteria);

    List<Venue> findByNameLike(String venueName);

    List<Venue> findByNameLike(TextCriteria criteria);

    @Query(fields = "{ 'name' : 1}")
    List<Venue> findTop10ByNameLikeIgnoreCase(String venueName);

    @Query(fields = "{'name':1,'city':1}")
    List<Venue> findTop10ByCityAndNameLikeIgnoreCase(String city, String category, String name);


    @Query(fields = "{'name':1,'city':1}")
    List<Venue> findTop10ByCityAndCategoryAndNameLikeIgnoreCase(String city, String category, String name);

    @Query(fields = "{ 'name' : 1}")
    List<Venue> findTop10ByNameOrCityLikeIgnoreCase(String venueName);

    List<Venue> findByName(String name);

    List<Venue> findByName(String name, Pageable pageable);

    List<Venue> findByName(TextCriteria textCriteria, Pageable pageable);

    Venue findById(String id);

    Venue findByAddress(String address);

    List<Venue> findByCategory(String category);

    List<Venue> findByNameAndAddressAllIgnoreCase(String name, String address);


    /**Geo location searching*/
    // {'geoNear' : 'location', 'near' : [x, y] }
    //GeoResults<Venue> findByLocationNear(Point location);

    // No metric: {'geoNear' : 'person', 'near' : [x, y], maxDistance : distance }
    // Metric: {'geoNear' : 'person', 'near' : [x, y], 'maxDistance' : distance,
    //          'distanceMultiplier' : metric.multiplier, 'spherical' : true }
    //GeoResults<Venue> findByLocationNear(Point location, Distance distance);

    // Metric: {'geoNear' : 'person', 'near' : [x, y], 'minDistance' : min,
    //          'maxDistance' : max, 'distanceMultiplier' : metric.multiplier,
    //          'spherical' : true }
    //GeoResults<Venue> findByLocationNear(Point location, Distance min, Distance max);

    // {'geoNear' : 'location', 'near' : [x, y] }
    //GeoResults<Venue> findByLocationNear(Point location);

}
