package se.lexicon.flightbooking_api.mapper;

import org.mapstruct.Mapper;
import se.lexicon.flightbooking_api.dto.flights.ItinerariesDTO;
import se.lexicon.flightbooking_api.entity.flights.Itineraries;

@Mapper(componentModel = "spring", uses = {LayoverMapper.class, DurationMapper.class, BagsMapper.class, DelayMapper.class, DurationMapper.class})
public interface ItinerariesMapper {
    ItinerariesDTO toDto(Itineraries itineraries);
    Itineraries toEntity(ItinerariesDTO itinerariesDTO);
}
