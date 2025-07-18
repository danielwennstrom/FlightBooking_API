package se.lexicon.flightbooking_api.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import se.lexicon.flightbooking_api.dto.flights.FlightDTO;
import se.lexicon.flightbooking_api.entity.flights.Flight;

@Mapper(componentModel = "spring", uses = {LayoverMapper.class, DurationMapper.class, FlightSegmentMapper.class, BagsMapper.class, DelayMapper.class})
public interface FlightMapper {
    @Mapping(source = "arrivalTime", target = "arrival")
    @Mapping(source = "departureTime", target = "departure")
    FlightDTO toDto(Flight flight);
    @InheritInverseConfiguration
    Flight toEntity(FlightDTO flightDTO);
}
