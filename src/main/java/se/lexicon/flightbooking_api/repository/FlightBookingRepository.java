package se.lexicon.flightbooking_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.lexicon.flightbooking_api.entity.FlightBooking;
import se.lexicon.flightbooking_api.entity.FlightStatus;
import se.lexicon.flightbooking_api.entity.booking.Booking;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface FlightBookingRepository extends JpaRepository<Booking, Long> {
//
//    // Find flights by status (available, booked, cancelled)
//    List<Booking> findByStatus(FlightStatus status);

    List<Booking> findBookingsByContactEmail(String contactEmail);

    List<Booking> findBookingById(UUID id);
//
//    // Find flights by departure time range
//    List<Booking> findByDepartureTimeBetween(LocalDateTime start, LocalDateTime end);
//
//    // Find flights by flight number
//    Booking findByFlightNumber(String flightNumber);
//
//    // Find available flights to a specific destination
//    List<Booking> findByStatusAndDestination(FlightStatus status, String destination);
//
//    // Find flights below a certain price
//    List<Booking> findByPriceLessThanEqual(Double maxPrice);
}