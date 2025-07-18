package se.lexicon.flightbooking_api.entity.flights;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Getter
public class FlightSegment {
    @JsonProperty("departure_airport")
    private FlightAirport departureAirport;

    @JsonProperty("arrival_airport")
    private FlightAirport arrivalAirport;

    @JsonProperty("duration_label")
    private String durationLabel;

    private Duration duration;
    private String airline;

    @JsonProperty("airline_logo")
    private String airlineLogo;

    @JsonProperty("flight_number")
    private String flightNumber;

    private String aircraft;
    private String seat;
    private String legroom;
    private List<String> extensions;
}
