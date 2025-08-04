package se.lexicon.flightbooking_api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import se.lexicon.flightbooking_api.dto.booking.BookingDTO;
import se.lexicon.flightbooking_api.entity.booking.Booking;

@Mapper(componentModel = "spring", uses = {FlightMapper.class, PassengerMapper.class})
public interface BookingMapper {
    @Mapping(target = "passengerList", ignore = true)
    Booking toEntity(BookingDTO dto);
    BookingDTO toDto(Booking booking);
}
