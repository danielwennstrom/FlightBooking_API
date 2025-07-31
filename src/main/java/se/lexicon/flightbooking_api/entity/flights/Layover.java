package se.lexicon.flightbooking_api.entity.flights;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
public class Layover {
    @Id
    @GeneratedValue
    private Long id;
    @JsonProperty("airport_code")
    private String airportCode;

    @JsonProperty("airport_name")
    private String airportName;

    @JsonProperty("duration_label")
    private String durationLabel;

    private int duration;
    private String city;
    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;
}
