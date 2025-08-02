package se.lexicon.flightbooking_api.service;

import jakarta.transaction.Transactional;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;
import se.lexicon.flightbooking_api.dto.booking.BookingDTO;
import se.lexicon.flightbooking_api.dto.booking.PassengerDTO;
import se.lexicon.flightbooking_api.entity.booking.Booking;
import se.lexicon.flightbooking_api.entity.booking.Passenger;
import se.lexicon.flightbooking_api.entity.flights.Flight;
import se.lexicon.flightbooking_api.entity.flights.FlightSegment;
import se.lexicon.flightbooking_api.entity.flights.Layover;
import se.lexicon.flightbooking_api.mapper.BookingMapper;
import se.lexicon.flightbooking_api.mapper.PassengerMapper;
import se.lexicon.flightbooking_api.repository.FlightBookingRepository;
import se.lexicon.flightbooking_api.repository.FlightRepository;

import java.util.List;
import java.util.UUID;

@Component
public class FlightBookingTools {

    private final FlightBookingRepository flightBookingRepository;
    private final FlightRepository flightRepository;
    private final BookingMapper bookingMapper;
    private final PassengerMapper passengerMapper;

    public FlightBookingTools(
            FlightBookingRepository flightBookingRepository, FlightRepository flightRepository,
            BookingMapper bookingMapper, PassengerMapper passengerMapper
    ) {
        this.flightBookingRepository = flightBookingRepository;
        this.flightRepository = flightRepository;
        this.bookingMapper = bookingMapper;
        this.passengerMapper = passengerMapper;
    }

    @Tool(description = "Search flight bookings by passenger e-mail")
    public List<BookingDTO> searchByPassengerEmail(String email) {
        return flightBookingRepository.findBookingsByContactEmail(email)
                .stream()
                .map(bookingMapper::toDto)
                .toList();
    }

    @Tool(description = "Search flight bookings by ID. Use if there are several bookings," +
            " and user provided part of or the whole booking ID, or to cancel the flight.")
    public BookingDTO searchById(UUID id) {
        return bookingMapper.toDto(flightBookingRepository.findBookingById(id));
    }

    @Tool(description = "Extract and structure booking info including passengers. MUST be used before bookFlight.")
    public Booking extractBookingInfo(
            BookingDTO bookingDto,
            List<PassengerDTO> passengerDataList
    ) {
        System.out.println("Launching extractBookingInfo");
        Booking bookingInfo = bookingMapper.toEntity(bookingDto);
        setPassengerAssociation(passengerDataList, bookingInfo);

        System.out.println("Extracted booking info: " + bookingInfo);
        return bookingInfo;
    }

    private void setPassengerAssociation(List<PassengerDTO> passengerDataList, Booking bookingInfo) {
        passengerDataList.forEach(dto -> {
            Passenger passenger = passengerMapper.toEntity(dto);
            passenger.setBooking(bookingInfo);
            bookingInfo.addPassenger(passenger);
        });
    }

    private Flight setFlightAssociations(Flight flight) {
        if (flight.getFlights() != null) {
            for (FlightSegment segment : flight.getFlights()) {
                segment.setFlight(flight);
            }
        }
        if (flight.getLayovers() != null) {
            for (Layover layover : flight.getLayovers()) {
                layover.setFlight(flight);
            }
        }
        return flight;
    }

    @Transactional
    @Tool(description = "Book the flight with booking info and passenger details, then save to database. NEVER use it before extractBookingInfo.")
    public BookingDTO bookFlight(BookingDTO bookingDTO,
                           List<PassengerDTO> passengerDataList) {
        System.out.println("Launching bookFlight");
        Booking booking = bookingMapper.toEntity(bookingDTO);
        
        setPassengerAssociation(passengerDataList, booking);

        if (booking.getDepartureFlight() != null) {
            Flight departureFlight = setFlightAssociations(booking.getDepartureFlight());
            flightRepository.save(departureFlight);
        }

        if (booking.getReturnFlight() != null) {
            Flight returnFlight = setFlightAssociations(booking.getReturnFlight());
            flightRepository.save(returnFlight);
        }

        try {
            Booking savedBooking = flightBookingRepository.saveAndFlush(booking);
            System.out.println("=== Booking saved successfully ===");
            System.out.println("Saved booking ID: " + savedBooking.getId());
            return bookingMapper.toDto(savedBooking);
        } catch (Exception e) {
            System.err.println("Error saving booking: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    @Tool(description = "Cancels the flight booking using the passenger's contact email")
    public String cancelFlight(BookingDTO bookingDto) {
        Booking booking = bookingMapper.toEntity(bookingDto);
        flightBookingRepository.delete(booking);
        return "Booking deleted";
    }

    @Tool(description = "Build the final booking confirmation message with relevant information to the user")
    public String buildBookingConfirmation(BookingDTO bookingDto) {
        StringBuilder sb = new StringBuilder();
        sb.append("Booking successful! 🎉\n");
        sb.append("Booking ID: ").append(bookingDto.getId()).append("\n");
        sb.append("Flight: ").append(bookingDto.getDeparture())
                .append(" to ").append(bookingDto.getDestination()).append("\n");
        sb.append("Departure date: ").append(bookingDto.getDepartureDate().toLocalDate()).append("\n");
        if (bookingDto.isRoundTrip()) {
            sb.append("Return date: ").append(bookingDto.getReturnDate().toLocalDate()).append("\n");
        }
        sb.append("Passengers:\n");
        for (PassengerDTO p : bookingDto.getPassengerList()) {
            sb.append("- ").append(p.getPassengerName()).append("\n");
        }
        sb.append("Confirmation email sent to: ").append(bookingDto.getContactEmail()).append("\n");
        sb.append("Total price: ").append(bookingDto.getTotalPrice()).append(" ").append(bookingDto.getCurrency());

        return sb.toString();
    }
}