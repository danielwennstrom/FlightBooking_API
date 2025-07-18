package se.lexicon.flightbooking_api.mapper;

import org.mapstruct.Mapper;
import se.lexicon.flightbooking_api.dto.flights.FlightAirportDTO;
import se.lexicon.flightbooking_api.entity.flights.FlightAirport;

@Mapper(componentModel = "spring")
public interface FlightAirportMapper {
    FlightAirportDTO toDto(FlightAirport flightAirport);
    FlightAirport toEntity(FlightAirportDTO flightAirportDTO);
}
