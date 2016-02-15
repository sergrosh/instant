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
@Service
public class SearchService {

    public Query getQuery(String query, String city, String category, int reviews,
                          List<String> speciality, String sortingType, Pageable page) {

        Query searchQuery = new Query();
        searchQuery.addCriteria(Criteria.where("name").regex(query));
        if (!city.isEmpty()) {
            searchQuery.addCriteria(Criteria.where("city").is(city));
        }
        if (!category.isEmpty()) {
            searchQuery.addCriteria(Criteria.where("category").is(category));
        }
        if (reviews != 0) {
            searchQuery.addCriteria(Criteria.where("reviews").is(reviews));
        }
        if (!speciality.isEmpty()) {
            searchQuery.addCriteria(Criteria.where("speciality").in(speciality));
        }
        if (sortingType.equals("reviews")) {
            searchQuery.with(new Sort(Sort.Direction.DESC, "reviews"));
        }
//        searchQuery.skip((page-1)*pageSize);
//        searchQuery.limit(pageSize);

        return searchQuery.with(page);
    }

}
