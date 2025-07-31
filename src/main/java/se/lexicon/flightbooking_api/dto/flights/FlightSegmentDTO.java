package se.lexicon.flightbooking_api.dto.flights;

import lombok.*;

import java.util.List;
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FlightSegmentDTO {
    private FlightAirportDTO departureAirport;
    private FlightAirportDTO arrivalAirport;
    private String durationLabel;
    private DurationDTO duration;
    private String airline;
    private String airlineLogo;
    private String flightNumber;
    private String aircraft;
    private String seat;
    private String legroom;
    private List<String> extensions;
}
