package se.lexicon.flightbooking_api.dto.flights;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import se.lexicon.flightbooking_api.deserializer.MultiFormatLocalDateTimeDeserializer;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FlightAirportDTO {
    private String airportName;
    private String airportCode;
    @JsonDeserialize(using = MultiFormatLocalDateTimeDeserializer.class)
    private LocalDateTime time;
}
