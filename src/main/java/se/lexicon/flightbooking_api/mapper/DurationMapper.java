package se.lexicon.flightbooking_api.mapper;

import org.mapstruct.Mapper;
import se.lexicon.flightbooking_api.dto.flights.DurationDTO;
import se.lexicon.flightbooking_api.entity.flights.Duration;

@Mapper(componentModel = "spring")
public interface DurationMapper {
    DurationDTO toDto(Duration duration);
    Duration toEntity(DurationDTO durationDTO);
}
