package se.lexicon.flightbooking_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.lexicon.flightbooking_api.entity.booking.Booking;

import java.util.List;
import java.util.UUID;

public interface FlightBookingRepository extends JpaRepository<Booking, UUID> {
    List<Booking> findBookingsByContactEmail(String contactEmail);

    Booking findBookingById(UUID id);
}