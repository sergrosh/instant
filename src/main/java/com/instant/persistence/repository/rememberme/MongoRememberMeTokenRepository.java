package com.instant.persistence.repository.rememberme;


import com.instant.persistence.model.MongoRememberMeToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author sroshchupkin
 */
@Repository
public interface MongoRememberMeTokenRepository extends CrudRepository<MongoRememberMeToken, String>, MongoRememberMeTokenRepositoryCustom {
    MongoRememberMeToken findBySeries(String series);
}
