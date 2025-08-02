package se.lexicon.flightbooking_api.entity.flights;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GoogleFlightsNextRequest {
    @JsonProperty("next_token")
    private String nextToken;
}
