package com.instant.config;

import com.instant.service.converter.datetime.LocalTimeToStringConverter;
import com.instant.service.converter.datetime.StringToLocalTimeConverter;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.convert.CustomConversions;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.ArrayList;
import java.util.List;


/**
 * Mongo configuration class
 *
 * @author sroshchupkin
 */

@EnableMongoRepositories("com.instant.persistence.repository")
@Configuration
public class MongoConfig extends AbstractMongoConfiguration {

    private static final String DATABASE = "spring.data.mongodb.database";
    private static final String HOST = "mongodb.host";

    @Autowired
    Environment environment;

    @Override
    protected String getDatabaseName() {
        return environment.getProperty(DATABASE);
    }

    @Override
    public Mongo mongo() throws Exception {
        return new MongoClient(environment.getProperty(HOST));
    }

    @Override
    public CustomConversions customConversions() {
        List<Converter<?, ?>> converters = new ArrayList<>();
        converters.add(new LocalTimeToStringConverter());
        converters.add(new StringToLocalTimeConverter());
        return new CustomConversions(converters);
    }
}

