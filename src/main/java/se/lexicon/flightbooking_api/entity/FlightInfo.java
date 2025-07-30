package se.lexicon.flightbooking_api.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Builder
@Data
public class FlightInfo {
    private String departure;
    private String destination;
    private LocalDate departureDate;
    private LocalDate returnDate;
    private Integer passengers;
    private String cabinClass;
    private Boolean isRoundTrip;

    public boolean isComplete() {
        return departure != null && destination != null && departureDate != null;
    }

    public List<String> getMissingFields() {
        List<String> missing = new ArrayList<>();
        if (departure == null) missing.add("departure location");
        if (destination == null) missing.add("destination");
        if (departureDate == null) missing.add("departure date");
        return missing;
    }
}
