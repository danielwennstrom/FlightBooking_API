package se.lexicon.flightbooking_api.entity.flights;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import se.lexicon.flightbooking_api.deserializer.MultiFormatLocalDateTimeDeserializer;
import se.lexicon.flightbooking_api.entity.booking.Booking;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Flight {
    @Id
    @GeneratedValue
    private Long id;
    @JsonProperty("departure_time")
    @JsonDeserialize(using = MultiFormatLocalDateTimeDeserializer.class)
    private LocalDateTime departureTime;

    @JsonProperty("arrival_time")
    @JsonDeserialize(using = MultiFormatLocalDateTimeDeserializer.class)
    private LocalDateTime arrivalTime;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "duration_id")
    private Duration duration;
    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL)
    private List<FlightSegment> flights;
    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL)
    private List<Layover> layovers;
    @Embedded
    private Bags bags;

    @Transient
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
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "delay_id")
    private Delay delay;

    @JsonProperty("self_transfer")
    private Boolean selfTransfer;
}
