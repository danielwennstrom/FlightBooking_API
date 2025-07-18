package se.lexicon.flightbooking_api.entity.flights;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;

@Getter
public class Layover {
    @JsonProperty("airport_code")
    private String airportCode;

    @JsonProperty("airport_name")
    private String airportName;

    @JsonProperty("duration_label")
    private String durationLabel;

    private int duration;
    private String city;
}
