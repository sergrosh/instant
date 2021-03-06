package com.instant.service.counter;

import com.instant.persistence.model.system.Counter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

/**
 * Counter service to communicate numeric identifier field which automatically increments every time new record is inserted
 *
 * @author Sergii Roshchupkin
 */
@Service
public class CounterServiceImpl implements CounterService {

    @Autowired
    private MongoOperations mongo;

    @Override
    public int getNextSequence(String collectionName) {
        Counter counter = mongo.findAndModify(
                query(where("_id").is(collectionName)),
                new Update().inc("seq", 1),
                options().returnNew(true),
                Counter.class);
        return counter.getSeq();
    }
}

