package se.lexicon.flightbooking_api.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import se.lexicon.flightbooking_api.dto.booking.BookingDTO;
import se.lexicon.flightbooking_api.dto.booking.PassengerDTO;
import se.lexicon.flightbooking_api.entity.booking.Booking;
import se.lexicon.flightbooking_api.entity.booking.Passenger;

//@Mapper(componentModel = "spring", uses = { FlightMapper.class })
@Mapper(componentModel = "spring", uses = { FlightMapper.class, PassengerMapper.class })
public interface BookingMapper {
    Booking toEntity(BookingDTO dto);
    BookingDTO toDto(Booking booking);

    @AfterMapping
    default void logAfterMapping(BookingDTO dto, @MappingTarget Booking entity) {
        System.out.println("Mapped Booking entity from DTO: " + entity);
    }
}
