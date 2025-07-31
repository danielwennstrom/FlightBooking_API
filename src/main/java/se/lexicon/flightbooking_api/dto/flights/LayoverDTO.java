package se.lexicon.flightbooking_api.dto.flights;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LayoverDTO {
    private String airportCode;
    private String airportName;
    private String durationLabel;
    private int duration;
    private String city;
}
