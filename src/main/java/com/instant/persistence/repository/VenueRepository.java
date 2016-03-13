package com.instant.persistence.repository;

import com.instant.persistence.model.venue.VenueEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

/**
 * @author sroshchupkin
 */
public interface VenueRepository extends MongoRepository<VenueEntity, String> {

    Page<VenueEntity> findAllBy(TextCriteria textCriteria, Pageable pageable);

    Page<VenueEntity> findByCity(String cityName, Pageable pageable);

    Page<VenueEntity> findByCityAndPublished(String cityName, Boolean published, Pageable pageable);

    @Query(fields = "{ 'name' : 1}")
    List<VenueEntity> findTop10ByNameLikeIgnoreCase(String venueName);

    @Query(fields = "{'name':1,'city':1}")
    List<VenueEntity> findTop10ByCityAndCategoryAndNameLikeIgnoreCase(String city, String category, String name);

    VenueEntity findById(String id);

//    GeoResults<Venue> findByLocationNear(Point location, Distance distance);

    /**Geo location searching*/
    // {'geoNear' : 'location', 'near' : [x, y] }
    //GeoResults<Venue> findByLocationNear(Point location);

    // No metric: {'geoNear' : 'person', 'near' : [x, y], maxDistance : distance }
    // Metric: {'geoNear' : 'person', 'near' : [x, y], 'maxDistance' : distance,
    //          'distanceMultiplier' : metric.multiplier, 'spherical' : true }
//    GeoResults<Venue> findByLocationNear(Point location, Distance distance);

    // Metric: {'geoNear' : 'person', 'near' : [x, y], 'minDistance' : min,
    //          'maxDistance' : max, 'distanceMultiplier' : metric.multiplier,
    //          'spherical' : true }
    //GeoResults<Venue> findByLocationNear(Point location, Distance min, Distance max);

    // {'geoNear' : 'location', 'near' : [x, y] }
    //GeoResults<Venue> findByLocationNear(Point location);

}
