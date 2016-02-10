package com.instant.persistence.repository.system;

import com.instant.persistence.model.system.Counter;
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
public class CounterServiceImpl implements CounterService {
    public static final String USER_ID_SEQUENCE_NAME = "user_id";

    private final MongoTemplate mongoTemplate;

    @Inject
    public CounterServiceImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public long getNextUserIdSequence() {
        return increaseCounter(USER_ID_SEQUENCE_NAME);
    }

    private long increaseCounter(String counterName) {
        Query query = new Query(Criteria.where("name").is(counterName));
        Update update = new Update().inc("sequence", 1);
        Counter counter = mongoTemplate.findAndModify(query, update, Counter.class); // return old Counter object
        if (counter == null) {
            counter = new Counter();
            counter.setName(counterName);
            counter.setSequence(2); //should increase by one.
            mongoTemplate.save(counter);
            return 1;
        }
        return counter.getSequence();
    }

}
