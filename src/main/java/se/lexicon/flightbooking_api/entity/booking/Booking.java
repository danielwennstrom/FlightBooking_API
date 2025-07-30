package se.lexicon.flightbooking_api.entity.booking;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import se.lexicon.flightbooking_api.entity.flights.Flight;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

@Data
@Entity
public class Booking {
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id = UUID.randomUUID();
    private LocalDateTime createdAt;
    private String contactEmail;

    // trip information from FlightInfo
    private String departure;
    private String destination;
    private LocalDateTime departureDate;
    private LocalDateTime returnDate;
    private int passengers;
    private String cabinClass;
    private boolean isRoundTrip;

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL)
    private List<Passenger> passengerList;
    
    private double totalPrice;
    private String currency;

    public Booking() {
        this.passengerList = new ArrayList<>();
        this.createdAt = LocalDateTime.now();
    }

    public Booking(String contactEmail, String departure, String destination,
                   LocalDateTime departureDate, int passengers, String cabinClass, boolean isRoundTrip) {
        this();
        this.contactEmail = contactEmail;
        this.departure = departure;
        this.destination = destination;
        this.departureDate = departureDate;
        this.passengers = passengers;
        this.cabinClass = cabinClass;
        this.isRoundTrip = isRoundTrip;
    }
    
    @PrePersist
    public void prePersist() {
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
    }
}