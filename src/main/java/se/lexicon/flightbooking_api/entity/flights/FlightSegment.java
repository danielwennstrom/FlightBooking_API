package se.lexicon.flightbooking_api.entity.flights;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Data
public class FlightSegment {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "departure_airport_id")
    @JsonProperty("departure_airport")
    private FlightAirport departureAirport;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "arrival_airport_id")
    @JsonProperty("arrival_airport")
    private FlightAirport arrivalAirport;

    @JsonProperty("duration_label")
    private String durationLabel;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "duration_id")
    private Duration duration;
    private String airline;

    @JsonProperty("airline_logo")
    private String airlineLogo;

    @JsonProperty("flight_number")
    private String flightNumber;

    private String aircraft;
    private String seat;
    private String legroom;
    @Transient
    private List<String> extensions;
    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;
}
