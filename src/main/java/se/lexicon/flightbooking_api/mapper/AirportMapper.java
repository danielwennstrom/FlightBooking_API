package se.lexicon.flightbooking_api.mapper;

import org.springframework.stereotype.Component;
import se.lexicon.flightbooking_api.dto.AirportDTO;
import se.lexicon.flightbooking_api.entity.Airport;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AirportMapper {
    public AirportDTO toDTO(Airport airport) {
        if (airport == null) return null;
        return new AirportDTO(
                airport.getIataCode(),
                airport.getIcaoCode(),
                airport.getName(),
                airport.getIsoCountry(),
                airport.getCountryName(),
                airport.getMunicipality(),
                airport.getDisplayName()
        );
    }

    public Airport toEntity(AirportDTO dto) {
        if (dto == null) return null;
        Airport airport = new Airport();
        airport.setIataCode(dto.getIataCode());
        airport.setIcaoCode(dto.getIcaoCode());
        airport.setName(dto.getName());
        airport.setIsoCountry(dto.getIsoCountry());
        airport.setMunicipality(dto.getMunicipality());
        return airport;
    }

    public List<AirportDTO> toDTOList(List<Airport> airports) {
        if (airports == null) return List.of();
        return airports.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<Airport> toEntityList(List<AirportDTO> dtos) {
        if (dtos == null) return List.of();
        return dtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
