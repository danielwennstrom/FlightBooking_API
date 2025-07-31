package se.lexicon.flightbooking_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.lexicon.flightbooking_api.entity.flights.Flight;

public interface FlightRepository extends JpaRepository<Flight, Long> {
}
