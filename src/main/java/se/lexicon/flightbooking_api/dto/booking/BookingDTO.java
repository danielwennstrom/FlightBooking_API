package se.lexicon.flightbooking_api.dto.booking;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import se.lexicon.flightbooking_api.deserializer.MultiFormatLocalDateTimeDeserializer;
import se.lexicon.flightbooking_api.dto.flights.FlightDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookingDTO {
    private UUID id;
    private String contactEmail;
    private String departure;
    private String destination;
    @JsonDeserialize(using = MultiFormatLocalDateTimeDeserializer.class)
    private LocalDateTime departureDate;
    @JsonDeserialize(using = MultiFormatLocalDateTimeDeserializer.class)
    private LocalDateTime returnDate;
    private int passengers;
    private String cabinClass;
    @JsonProperty
    public boolean isRoundTrip() {
        return returnDate != null;
    }
    private FlightDTO departureFlight;
    private FlightDTO returnFlight;
    private List<PassengerDTO> passengerList;

    private double totalPrice;
    private String currency;
}
