package com.instant.config;

import com.instant.common.ArgumentResolverComposite;
import com.instant.controller.InstantErrorController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * @author sroshchupkin
 */

@Configuration
public class MvcConfiguration extends WebMvcConfigurerAdapter {

    @Autowired
    private ArgumentResolverComposite argumentResolverComposite;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(argumentResolverComposite);
    }

    @Autowired
    private ErrorAttributes errorAttributes;

    @Bean
    public InstantErrorController instantErrorController(){return new InstantErrorController(errorAttributes);}
}
