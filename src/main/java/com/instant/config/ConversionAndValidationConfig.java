package com.instant.config;

import com.instant.service.converter.NewVenueConverter;
import com.instant.service.converter.VenueConverter;
import com.instant.service.converter.VenueEntityConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.HashSet;
import java.util.Set;

/**
 * @author sroshchupkin
 */

@Configuration
public class ConversionAndValidationConfig {

    @Bean(name = "conversionService")
    public ConversionService getConversionService() {
        ConversionServiceFactoryBean bean = new ConversionServiceFactoryBean();
        bean.setConverters(getConverters());
        bean.afterPropertiesSet();
        ConversionService object = bean.getObject();
        return object;
    }

    private Set<Converter> getConverters() {
        Set<Converter> converters = new HashSet<>();
        converters.add(new VenueConverter());
        converters.add(new VenueEntityConverter());
        converters.add(new NewVenueConverter());
        return converters;
    }

    @Bean(name = "validator")
    public javax.validation.Validator getValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        return validator;
    }
}
