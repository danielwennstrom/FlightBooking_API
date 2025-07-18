package se.lexicon.flightbooking_api.mapper;

import org.mapstruct.Mapper;
import se.lexicon.flightbooking_api.dto.flights.DelayDTO;
import se.lexicon.flightbooking_api.entity.flights.Delay;

@Mapper(componentModel = "spring")
public interface DelayMapper {
    DelayDTO toDto(Delay delay);
    Delay toEntity(DelayDTO delayDTO);
}
