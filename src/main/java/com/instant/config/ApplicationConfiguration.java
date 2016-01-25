package com.instant.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;

/**
 * @author sroshchupkin
 */

@Configuration
@EnableCaching
@EnableAsync
@EnableScheduling
@ComponentScan(basePackages = "com.instant", excludeFilters = {@ComponentScan.Filter(Configuration.class), @ComponentScan.Filter(Controller.class)})
@EnableMongoRepositories("com.instant.persistence.repository")
@PropertySource({"classpath:application.properties"})
public class ApplicationConfiguration {
}
