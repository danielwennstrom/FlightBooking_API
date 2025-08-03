package se.lexicon.flightbooking_api.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class PriceDeserializer extends JsonDeserializer<Double> {
    public Double deserialize(JsonParser p, DeserializationContext ctx) throws IOException {
        String value = p.getText();
        return "unavailable".equals(value) ? null : Double.valueOf(value);
    }
}