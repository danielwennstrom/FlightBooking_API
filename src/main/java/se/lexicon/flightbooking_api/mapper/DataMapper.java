package se.lexicon.flightbooking_api.mapper;

import org.mapstruct.Mapper;
import se.lexicon.flightbooking_api.dto.flights.DataDTO;
import se.lexicon.flightbooking_api.entity.flights.Data;

@Mapper(componentModel = "spring")
public interface DataMapper {
    DataDTO toDto(Data data);
    Data toEntity(DataDTO dataDTO);
}
