package com.instant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;

/**
 * @author sroshchupkin
 */
@Configuration
@SpringBootApplication
@EnableAutoConfiguration
public class InstantApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(InstantApplication.class, args);
    }
}
