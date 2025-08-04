package se.lexicon.flightbooking_api.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class MultiFormatLocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {
    private static final List<DateTimeFormatter> FORMATTERS = Arrays.asList(
            DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm a", Locale.ENGLISH),
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"),
            DateTimeFormatter.ofPattern("yyyy-M-d H:mm"),
            DateTimeFormatter.ISO_LOCAL_DATE_TIME,
            DateTimeFormatter.ISO_LOCAL_DATE
    );

    @Override
    public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonToken token = p.getCurrentToken();

        // handle array format: [year, month, day, hour, minute] or [year, month, day, hour, minute, second]
        if (token == JsonToken.START_ARRAY) {
            JsonNode arrayNode = p.getCodec().readTree(p);
            if (arrayNode.isArray() && arrayNode.size() >= 3) {
                int year = arrayNode.get(0).asInt();
                int month = arrayNode.get(1).asInt();
                int day = arrayNode.get(2).asInt();
                int hour = arrayNode.size() > 3 ? arrayNode.get(3).asInt() : 0;
                int minute = arrayNode.size() > 4 ? arrayNode.get(4).asInt() : 0;
                int second = arrayNode.size() > 5 ? arrayNode.get(5).asInt() : 0;

                return LocalDateTime.of(year, month, day, hour, minute, second);
            } else {
                throw new IOException("Invalid date array format. Expected at least [year, month, day]");
            }
        }
        
        if (token == JsonToken.VALUE_STRING) {
            String dateStr = p.getText().trim();

            if (dateStr.matches("\\d{2}-\\d{2}-\\d{4}\\d{1,2}:\\d{2} [AP]M")) {
                dateStr = dateStr.replaceFirst("(\\d{2}-\\d{2}-\\d{4})(\\d{1,2}:\\d{2} [AP]M)", "$1 $2");
            }

            for (DateTimeFormatter formatter : FORMATTERS) {
                try {
                    if (formatter == DateTimeFormatter.ISO_LOCAL_DATE) {
                        return LocalDate.parse(dateStr, formatter).atStartOfDay();
                    } else {
                        return LocalDateTime.parse(dateStr, formatter);
                    }
                } catch (DateTimeParseException e) {
                    // try next formatter
                }
            }

            throw new IOException("Unrecognized date format: " + dateStr);
        }

        throw new IOException("Expected string or array for LocalDateTime, got: " + token);
    }
}