package se.lexicon.flightbooking_api.entity.booking;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
public class Passenger {
    @Id
    @GeneratedValue
    private Long id;
    private String passengerName;
    @Nullable
    private String passengerEmail;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id")
    @JsonBackReference
    private Booking booking;
}
