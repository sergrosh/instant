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
public class SearchServiceImpl implements SearchService {

    @Override
    public Query getQuery(String query, String city, String category, double rating,
                          List<String> speciality, String sortingType, Pageable page) {

        Query searchQuery = new Query();

        searchQuery.addCriteria(Criteria.where("city").is(city));

        if (!query.isEmpty()) {
            searchQuery.addCriteria(Criteria.where("name").regex(query));
        }
        if (!category.isEmpty()) {
            searchQuery.addCriteria(Criteria.where("category").is(category));
        }
        if (rating != 0) {
            searchQuery.addCriteria(Criteria.where("rating").is(rating));
        }
        if (!speciality.isEmpty()) {
            searchQuery.addCriteria(Criteria.where("speciality").in(speciality));
        }
        if (!sortingType.equals("")) {
            switch (sortingType) {
                case "rating":
                    searchQuery.with(new Sort(Sort.Direction.DESC, "rating"));
                    break;
                case "popularity":
                    searchQuery.with(new Sort(Sort.Direction.DESC, "clickouts"));
                    break;
                case "price_asc":
                    searchQuery.with(new Sort(Sort.Direction.ASC, "avgPrice"));
                    break;
                case "price_desc":
                    searchQuery.with(new Sort(Sort.Direction.DESC, "avgPrice"));
                    break;
                default:
                    searchQuery.with(new Sort(Sort.Direction.DESC, "clickouts"));
                    break;
            }
        }
//        searchQuery.skip((page-1)*pageSize);
//        searchQuery.limit(pageSize);

        return searchQuery.with(page);
    }
}
