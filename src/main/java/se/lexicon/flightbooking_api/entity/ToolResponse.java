package se.lexicon.flightbooking_api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import se.lexicon.flightbooking_api.dto.flights.FlightDTO;
import se.lexicon.flightbooking_api.entity.flights.Flight;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ToolResponse {
    private String type;
    private String message;
    @JsonProperty("data")
    private List<FlightDTO> flightData;

    public ToolResponse(String type, String message) {
        this.type = type;
        this.message = message;
    }
}
