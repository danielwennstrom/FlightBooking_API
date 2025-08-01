package se.lexicon.flightbooking_api.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import se.lexicon.flightbooking_api.dto.booking.BookingDTO;
import se.lexicon.flightbooking_api.entity.booking.Booking;

@Mapper(componentModel = "spring", uses = {FlightMapper.class, PassengerMapper.class})
public interface BookingMapper {
    Booking toEntity(BookingDTO dto);

    BookingDTO toDto(Booking booking);
//
//    @AfterMapping
//    default void normalizeEntity(@MappingTarget Booking entity, BookingDTO dto) {
//        entity.setRoundTrip(dto.getReturnDate() != null);
//    }
//    
//    @AfterMapping
//    default void normalizeDto(@MappingTarget BookingDTO dto, Booking booking) {
//        dto.setRoundTrip(booking.getReturnDate() != null);
//    }
}
