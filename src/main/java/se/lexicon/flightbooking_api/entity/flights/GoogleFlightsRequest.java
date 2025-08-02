package se.lexicon.flightbooking_api.entity.flights;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class GoogleFlightsRequest {
    @JsonProperty("departure_id")
    private String departureId;
    @JsonProperty("arrival_id")
    private String arrivalId;
    @JsonProperty("outbound_date")
    private LocalDate outboundDate;
    @JsonProperty("return_date")
    private LocalDate returnDate;
    @JsonProperty("travel_class")
    private String travelClass;
    @JsonProperty("adults")
    private int passengers;
}
