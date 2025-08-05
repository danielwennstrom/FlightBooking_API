package se.lexicon.flightbooking_api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import se.lexicon.flightbooking_api.dto.flights.FlightAirportDTO;
import se.lexicon.flightbooking_api.entity.flights.FlightAirport;

@Mapper(componentModel = "spring")
public interface FlightAirportMapper {
    FlightAirportDTO toDto(FlightAirport flightAirport);
    @Mapping(source = "time", target = "time")
    FlightAirport toEntity(FlightAirportDTO flightAirportDTO);
}
