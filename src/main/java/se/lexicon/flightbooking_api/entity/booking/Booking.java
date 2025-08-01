package se.lexicon.flightbooking_api.entity.booking;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import se.lexicon.flightbooking_api.deserializer.MultiFormatLocalDateTimeDeserializer;
import se.lexicon.flightbooking_api.entity.FlightInfo;
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
    private UUID id;
    @Setter(AccessLevel.NONE)
    private LocalDateTime createdAt;
    private String contactEmail;

    // trip information from FlightInfo
    private String departure;
    private String destination;
    @JsonDeserialize(using = MultiFormatLocalDateTimeDeserializer.class)
    private LocalDateTime departureDate;
    @JsonDeserialize(using = MultiFormatLocalDateTimeDeserializer.class)
    private LocalDateTime returnDate;
    private int passengers;
    private String cabinClass;
    private boolean isRoundTrip;
    @ManyToOne
    @JoinColumn(name = "departure_flight_id")
    private Flight departureFlight;
    @ManyToOne
    @JoinColumn(name = "return_flight_id")
    private Flight returnFlight;

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
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
        this.createdAt = LocalDateTime.now();
        this.id = UUID.randomUUID();
    }

    public void addPassenger(Passenger passenger) {
        passenger.setBooking(this);
        this.passengerList.add(passenger);
    }
}