package com.instant.persistence.repository.system;

import com.instant.persistence.model.system.UserCounter;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * @author sroshchupkin
 */
@Service
public class UserCounterServiceImpl implements UserCounterService {
    public static final String USER_ID_SEQUENCE_NAME = "user_id";

    private final MongoTemplate mongoTemplate;

    @Inject
    public UserCounterServiceImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public long getNextUserIdSequence() {
        return increaseCounter(USER_ID_SEQUENCE_NAME);
    }

    private long increaseCounter(String counterName) {
        Query query = new Query(Criteria.where("name").is(counterName));
        Update update = new Update().inc("sequence", 1);
        UserCounter userCounter = mongoTemplate.findAndModify(query, update, UserCounter.class); // return old UserCounter object
        if (userCounter == null) {
            userCounter = new UserCounter();
            userCounter.setName(counterName);
            userCounter.setSequence(2); //should increase by one.
            mongoTemplate.save(userCounter);
            return 1;
        }
        return userCounter.getSequence();
    }

}
