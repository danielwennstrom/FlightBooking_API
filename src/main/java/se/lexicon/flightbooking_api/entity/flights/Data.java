package se.lexicon.flightbooking_api.entity.flights;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class Data {
    @JsonProperty("itineraries")
    private Itineraries itineraries;

    @JsonProperty("priceHistory")
    private PriceHistory priceHistory;
}
