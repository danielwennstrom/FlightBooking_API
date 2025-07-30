package se.lexicon.flightbooking_api.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import se.lexicon.flightbooking_api.dto.AirportDTO;
import se.lexicon.flightbooking_api.entity.Airport;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface AirportMapper {
    Airport toDto(AirportDTO dto);
    AirportDTO toEntity(Airport entity);
    List<AirportDTO> toDTOList(List<Airport> airports);
    List<Airport> toEntityList(List<AirportDTO> dtos);
}
