package se.lexicon.flightbooking_api.dto.flights;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class FlightDTO {
    private String departure;
    private String arrival;
    private DurationDTO duration;
    private List<FlightSegmentDTO> flights;
    private List<LayoverDTO> layovers;
    private BagsDTO bags;
    private double price;
    private int stops;
    private String airlineLogo;
    private String nextToken;
    private String bookingToken;
    private DelayDTO delay;
}
