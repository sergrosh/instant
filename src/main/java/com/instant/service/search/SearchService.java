package com.instant.service.search;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author sroshchupkin
 */

public interface SearchService {

    Query getQuery(String query, String city, String category, double rating,
                          List<String> speciality, String sortingType, Pageable page);

}
