package se.lexicon.flightbooking_api.dto.flights;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LayoverDTO {
    private String airportCode;
    private String airportName;
    private String durationLabel;
    private int duration;
    private String city;
}
