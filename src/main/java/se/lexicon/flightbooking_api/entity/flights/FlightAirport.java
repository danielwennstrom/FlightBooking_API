package se.lexicon.flightbooking_api.entity.flights;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import se.lexicon.flightbooking_api.deserializer.MultiFormatLocalDateTimeDeserializer;
import se.lexicon.flightbooking_api.entity.Airport;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class FlightAirport {
    @Id
    @GeneratedValue
    private Long id;
    @JsonProperty("airport_name")
    private String airportName;

    @JsonProperty("airport_code")
    private String airportCode;

    @JsonDeserialize(using = MultiFormatLocalDateTimeDeserializer.class)
    private LocalDateTime time;
//    @ManyToOne
//    @JoinColumn(name = "flight_segment_id")
//    private FlightSegment flightSegment;

    public Airport findMatchingAirport(List<Airport> airportDatabase) {
        if (airportCode == null) return null;

        return airportDatabase.stream()
                .filter(airport -> airportCode.equals(airport.getIataCode()) ||
                        airportCode.equals(airport.getIcaoCode()))
                .findFirst()
                .orElse(null);
    }
    
    public static FlightAirport fromAirport(Airport airport, String time) {
        FlightAirport flightAirport = new FlightAirport();
        flightAirport.setAirportName(airport.getName());
        flightAirport.setAirportCode(airport.getIataCode() != null ? airport.getIataCode() : airport.getIcaoCode());
        flightAirport.setTime(LocalDateTime.parse(time));
        return flightAirport;
    }
}
