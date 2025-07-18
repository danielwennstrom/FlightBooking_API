package se.lexicon.flightbooking_api.mapper;

import org.mapstruct.Mapper;
import se.lexicon.flightbooking_api.dto.flights.GoogleFlightsResponseDTO;
import se.lexicon.flightbooking_api.entity.flights.GoogleFlightsResponse;

@Mapper(componentModel = "spring", uses = FlightMapper.class)
public interface GoogleFlightsResponseMapper {
    GoogleFlightsResponseDTO toDto(GoogleFlightsResponse googleFlightsResponse);
    GoogleFlightsResponse toEntity(GoogleFlightsResponseDTO googleFlightsResponseDTO);
}
