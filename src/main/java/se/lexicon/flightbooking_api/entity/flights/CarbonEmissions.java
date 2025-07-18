package se.lexicon.flightbooking_api.entity.flights;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class CarbonEmissions {
    @JsonProperty("difference_percent")
    private int differencePercent;

    @JsonProperty("CO2e")
    private long co2e;

    @JsonProperty("typical_for_this_route")
    private long typicalForThisRoute;

    private long higher;
}
