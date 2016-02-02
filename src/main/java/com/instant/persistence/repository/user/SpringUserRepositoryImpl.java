package com.instant.persistence.repository.user;


import com.instant.persistence.model.SpringUser;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * @author sroshchupkin
 */
public class SpringUserRepositoryImpl implements SpringUserRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public boolean isSpringUserExists(String username, String email) {
        Query query = Query.query(Criteria.where("email").is(email).orOperator(Criteria.where("username").is(username)));
        return mongoTemplate.count(query, SpringUser.class) > 0;
    }

    @Override
    public boolean isExistsByEmail(String email) {
        return mongoTemplate.count(Query.query(Criteria.where("email").is(email)), SpringUser.class) > 0;
    }

    @Override
    public boolean isExistsByUsername(String username) {
        return mongoTemplate.count(Query.query(Criteria.where("username").is(username)), SpringUser.class) > 0;
    }

    @Override
    public Iterable<SpringUser> search(String search) {
        if (StringUtils.isEmpty(search)) {
            return mongoTemplate.findAll(SpringUser.class);
        } else {
            Pattern pattern = Pattern.compile(search, Pattern.CASE_INSENSITIVE);
            //todo orOperator won't work :(
            Set<SpringUser> users = new HashSet<>(mongoTemplate.find(Query.query(Criteria.where("username").regex(pattern)), SpringUser.class));
            users.addAll(mongoTemplate.find(Query.query(Criteria.where("email").regex(pattern)), SpringUser.class));
            users.addAll(mongoTemplate.find(Query.query(Criteria.where("roles").regex(pattern)), SpringUser.class));
            return users;
        }
    }
}
