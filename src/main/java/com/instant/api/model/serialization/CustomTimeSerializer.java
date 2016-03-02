package com.instant.api.model.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;

import java.io.IOException;


/**
 * custom time serializer -> serialize to HH:mm format
 *
 * @author Sergii Roshchupkin
 */
public class CustomTimeSerializer extends JsonSerializer<LocalTime> {
    DateTimeFormatter outputFormat = new DateTimeFormatterBuilder().appendPattern("HH:mm").toFormatter();

    @Override
    public void serialize(LocalTime value, JsonGenerator jgen, SerializerProvider provider)
            throws IOException {
        String formatted = value.toString(outputFormat);
        jgen.writeString(formatted);
    }
}
