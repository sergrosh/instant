package com.instant.persistence.repository.rememberme;


import com.instant.persistence.model.MongoRememberMeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

/**
 * @author sroshchupkin
 */
@Component
public class MongoRememberMeTokenRepositoryImpl implements MongoRememberMeTokenRepositoryCustom {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void removeUserTokens(String username) {
        Query query = new Query();
        query.addCriteria(Criteria.where("username").exists(true)
                .andOperator(Criteria.where("username").is(username)
                ));
        mongoTemplate.remove(query, MongoRememberMeToken.class);
    }
}
