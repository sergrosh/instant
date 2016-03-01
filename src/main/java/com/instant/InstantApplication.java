package com.instant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * @author sroshchupkin
 */
@Configuration
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
@EnableSwagger2
public class InstantApplication {

    public static void main(String[] args) {
        SpringApplication.run(InstantApplication.class, args);
    }
}
