package com.instant.service.converter.datetime;

import org.joda.time.LocalTime;
import org.springframework.core.convert.converter.Converter;

/**
 * @author sroshchupkin
 */
public class StringToLocalTimeConverter implements Converter<String, LocalTime> {
    @Override
    public LocalTime convert(String s) {
        return LocalTime.parse(s);
    }
}
