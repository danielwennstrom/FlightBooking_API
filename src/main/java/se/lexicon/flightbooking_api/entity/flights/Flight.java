package se.lexicon.flightbooking_api.entity.flights;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Flight {
    @JsonProperty("departure_time")
    private String departureTime;

    @JsonProperty("arrival_time")
    private String arrivalTime;

    private Duration duration;
    private List<FlightSegment> flights;
    private List<Layover> layovers;
    private Bags bags;

    @JsonProperty("carbon_emissions")
    private CarbonEmissions carbonEmissions;

    private double price;
    private int stops;

    @JsonProperty("airline_logo")
    private String airlineLogo;

    @JsonProperty("next_token")
    private String nextToken;

    @JsonProperty("booking_token")
    private String bookingToken;
    
    private Delay delay;

    @JsonProperty("self_transfer")
    private Boolean selfTransfer;
}
