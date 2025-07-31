package se.lexicon.flightbooking_api.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class FlexibleDateParser {

    private static final List<DateTimeFormatter> FORMATTERS = Arrays.asList(
            DateTimeFormatter.ofPattern("yyyy-M-d HH:mm"),       // 2025-7-22 20:47
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"),     // 2025-07-22 20:47
            DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm a"),   // 22-07-2025 08:47 PM
            DateTimeFormatter.ISO_LOCAL_DATE_TIME                // 2025-07-22T20:47:00
    );

    public static LocalDateTime parseFlexible(String dateStr) {
        if (dateStr == null || dateStr.isBlank()) return null;
        for (DateTimeFormatter formatter : FORMATTERS) {
            try {
                return LocalDateTime.parse(dateStr.trim(), formatter);
            } catch (Exception ignored) {}
        }
        System.err.println("Unrecognized date format: " + dateStr);
        return null;
    }
}
