package se.lexicon.flightbooking_api.dto.flights;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import se.lexicon.flightbooking_api.deserializer.MultiFormatLocalDateTimeDeserializer;

import java.time.LocalDateTime;
import java.util.List;
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FlightDTO {
    @JsonDeserialize(using = MultiFormatLocalDateTimeDeserializer.class)
    private LocalDateTime departure;
    @JsonDeserialize(using = MultiFormatLocalDateTimeDeserializer.class)
    private LocalDateTime arrival;
    private DurationDTO duration;
    private List<FlightSegmentDTO> flights;
    private List<LayoverDTO> layovers;
    private BagsDTO bags;
    private double price;
    private int stops;
    private String airlineLogo;
    private String nextToken;
    private String bookingToken;
    private DelayDTO delay;
}
