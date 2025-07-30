package se.lexicon.flightbooking_api.entity.booking;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Passenger {
    @Id
    @GeneratedValue
    private Long id;
    private String passengerName;
    @Nullable
    private String passengerEmail;
    @ManyToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;
}
