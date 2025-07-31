package se.lexicon.flightbooking_api.entity.flights;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
public class Delay {
    @Id
    @GeneratedValue
    private Long id;
    @JsonProperty("values")
    private boolean delayValues;
    private int text;
//    @ManyToOne
//    @JoinColumn(name = "flight_id")
//    private Flight flight;
}
