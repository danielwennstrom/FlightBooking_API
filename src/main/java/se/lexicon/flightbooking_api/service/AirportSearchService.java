package se.lexicon.flightbooking_api.service;

import se.lexicon.flightbooking_api.entity.Airport;

import java.util.List;
import java.util.Optional;

public interface AirportSearchService {
    List<Airport> searchAirports(String query);
    List<Airport> searchAirports(String query, int limit);
    List<Airport> getAirportsByCountry(String countryCode);
    Optional<Airport> getAirportByIataCode(String iataCode);
    Optional<Airport> getAirportByIcaoCode(String icaoCode);
    List<Airport> getAllAirports();
    List<Airport> getLimitedAirports(int limit);
    long getAirportCount();
    List<String> getAvailableCountries();
}
