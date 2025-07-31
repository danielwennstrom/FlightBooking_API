package se.lexicon.flightbooking_api.entity.flights;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Data
public class Bags {
    @JsonProperty("carry_on")
    private Integer carryOn;
    private Integer checked;
//    @ManyToOne
//    @JoinColumn(name = "flight_id")
//    private Flight flight;
}
