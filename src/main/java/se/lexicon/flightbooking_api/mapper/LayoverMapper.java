package se.lexicon.flightbooking_api.mapper;

import org.mapstruct.Mapper;
import se.lexicon.flightbooking_api.dto.flights.LayoverDTO;
import se.lexicon.flightbooking_api.entity.flights.Layover;

@Mapper(componentModel = "spring")
public interface LayoverMapper {
    LayoverDTO toDto(Layover layover);
    Layover toEntity(LayoverDTO layoverDTO);
}
