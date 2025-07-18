package se.lexicon.flightbooking_api.entity.flights;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import se.lexicon.flightbooking_api.entity.Airport;

@Getter
@Setter
public class FlightAirport {
    @JsonProperty("airport_name")
    private String airportName;

    @JsonProperty("airport_code")
    private String airportCode;

    private String time;
    
    public Airport findMatchingAirport(java.util.List<Airport> airportDatabase) {
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
        flightAirport.setTime(time);
        return flightAirport;
    }
}
