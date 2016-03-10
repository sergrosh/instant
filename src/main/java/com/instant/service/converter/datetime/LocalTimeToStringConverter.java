package com.instant.service.converter.datetime;

import org.joda.time.LocalTime;
import org.springframework.core.convert.converter.Converter;

/**
 * @author sroshchupkin
 */
public class LocalTimeToStringConverter implements Converter<LocalTime, String> {
    @Override
    public String convert(LocalTime localTime) {
        return localTime.toString();
    }
}
