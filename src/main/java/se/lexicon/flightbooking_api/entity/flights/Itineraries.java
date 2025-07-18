package se.lexicon.flightbooking_api.entity.flights;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class Itineraries {
    @JsonProperty("topFlights")
    private List<Flight> topFlights;

    @JsonProperty("otherFlights")
    private List<Flight> otherFlights;
}
