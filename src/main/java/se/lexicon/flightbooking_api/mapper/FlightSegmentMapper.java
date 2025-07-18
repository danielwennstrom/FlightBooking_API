package se.lexicon.flightbooking_api.mapper;

import org.mapstruct.Mapper;
import se.lexicon.flightbooking_api.dto.flights.FlightSegmentDTO;
import se.lexicon.flightbooking_api.entity.flights.FlightSegment;

@Mapper(componentModel = "spring")
public interface FlightSegmentMapper {
    FlightSegmentDTO toDto(FlightSegment flightSegment);
    FlightSegment toEntity(FlightSegmentDTO flightSegmentDto);
}
