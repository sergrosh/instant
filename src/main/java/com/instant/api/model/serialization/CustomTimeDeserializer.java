package com.instant.api.model.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.joda.time.LocalTime;

import java.io.IOException;

/**
 * Custom time deserializer
 *
 * @author Sergii Roshchupkin
 */
public class CustomTimeDeserializer extends JsonDeserializer<LocalTime> {
    @Override
    public LocalTime deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        return new LocalTime(jp.getText());
    }
}
