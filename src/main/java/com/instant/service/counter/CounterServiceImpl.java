package com.instant.service.counter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import static org.springframework.data.mongodb.core.query.Query.*;
import static org.springframework.data.mongodb.core.query.Criteria.*;
import static org.springframework.data.mongodb.core.FindAndModifyOptions.*;

/**
 * Counter service to communicate numeric identifier field which automatically increments every time new record is inserted
 *
 * @author Sergii Roshchupkin
 */
public class CounterServiceImpl implements CounterService {

    @Service
    public class CounterService {
        @Autowired
        private MongoOperations mongo;

        public int getNextSequence(String collectionName) {
//            Counter counter = mongo.findAndModify(
//                    query(where("_id").is(collectionName)),
//                    new Update  ().inc("seq", 1),
//                    options().returnNew(true),
//                    Counter.class);
//
//            return counter.getSeq();
            return 1;
        }
    }
}
