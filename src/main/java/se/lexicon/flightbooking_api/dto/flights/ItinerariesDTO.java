package se.lexicon.flightbooking_api.dto.flights;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class ItinerariesDTO {
    private List<FlightDTO> topFlights;
    private List<FlightDTO> otherFlights;
}
