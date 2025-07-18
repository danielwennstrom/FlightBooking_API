package se.lexicon.flightbooking_api.entity.flights;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;

@Getter
public class Bags {
    @JsonProperty("carry_on")
    private Integer carryOn;

    private Integer checked;
}
