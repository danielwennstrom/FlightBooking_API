package se.lexicon.flightbooking_api.mapper;

import org.mapstruct.Mapper;
import se.lexicon.flightbooking_api.dto.flights.BagsDTO;
import se.lexicon.flightbooking_api.entity.flights.Bags;

@Mapper(componentModel = "spring")
public interface BagsMapper {
    BagsDTO toDto(Bags bags);
    Bags toEntity(BagsDTO bagsDTO);
}
