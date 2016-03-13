package com.instant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author sroshchupkin
 */
@SpringBootApplication
@EnableSwagger2
@EnableScheduling
public class InstantApplication {
    public static void main(String[] args) {
        SpringApplication.run(InstantApplication.class, args);
    }
}
