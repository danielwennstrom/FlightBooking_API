package se.lexicon.flightbooking_api.entity.flights;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
public class Duration {
    @Id
    @GeneratedValue
    private Long id;
    private int raw;
    private String text;
//    @ManyToOne
//    @JoinColumn(name = "flight_id")
//    private Flight flight;
}
