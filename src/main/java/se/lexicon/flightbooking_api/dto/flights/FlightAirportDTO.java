package se.lexicon.flightbooking_api.dto.flights;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FlightAirportDTO {
    private String airportName;
    private String airportCode;
    private String time;
}
