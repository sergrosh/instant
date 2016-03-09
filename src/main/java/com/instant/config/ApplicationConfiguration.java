package com.instant.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * @author sroshchupkin
 */

@Configuration
public class ApplicationConfiguration {

    // swagger config
    @Bean
    public Docket customImplementation() {

        ApiInfo apiInfo = new ApiInfo("Venue API", "Protype API for management of venues and images", "1.0.0", "", "", "", "");
        return
                new Docket(DocumentationType.SWAGGER_2)
                        .apiInfo(apiInfo)
                        .select()
                        .apis(RequestHandlerSelectors.basePackage("com.instant.controller.rest"))
                        .build();
    }
}
