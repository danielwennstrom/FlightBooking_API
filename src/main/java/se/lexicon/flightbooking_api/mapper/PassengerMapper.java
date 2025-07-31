package se.lexicon.flightbooking_api.mapper;

import org.mapstruct.Mapper;
import se.lexicon.flightbooking_api.dto.booking.PassengerDTO;
import se.lexicon.flightbooking_api.entity.booking.Passenger;

@Mapper(componentModel = "spring")
public interface PassengerMapper {
    Passenger toEntity(PassengerDTO dto);
    PassengerDTO toDto(Passenger passenger);
}
