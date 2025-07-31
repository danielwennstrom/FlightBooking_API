package se.lexicon.flightbooking_api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import se.lexicon.flightbooking_api.dto.flights.FlightAirportDTO;
import se.lexicon.flightbooking_api.entity.flights.FlightAirport;
import se.lexicon.flightbooking_api.parser.FlexibleDateParser;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface FlightAirportMapper {
    FlightAirportDTO toDto(FlightAirport flightAirport);
    @Mapping(source = "time", target = "time", qualifiedByName = "parseTime")
    FlightAirport toEntity(FlightAirportDTO flightAirportDTO);


    @Named("parseTime")
    default LocalDateTime parseTime(String timeStr) {
        return FlexibleDateParser.parseFlexible(timeStr);
    }
}
