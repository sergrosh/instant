package com.instant.api.model.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;

import java.io.IOException;

/**
 * Custom time deserializer
 *
 * @author Sergii Roshchupkin
 */
public class CustomTimeDeserializer extends JsonDeserializer<LocalTime>{
    DateTimeFormatter outputFormat = new DateTimeFormatterBuilder().appendPattern("HH:MM:SS").toFormatter();

    @Override
    public LocalTime deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException{
        LocalTime outputLocalTime = outputFormat.parseLocalTime(jp.getText()+":00");
        return outputLocalTime;
    }
}
