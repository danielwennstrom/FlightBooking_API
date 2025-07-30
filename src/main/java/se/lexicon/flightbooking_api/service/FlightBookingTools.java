package se.lexicon.flightbooking_api.service;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;
import se.lexicon.flightbooking_api.entity.booking.Booking;
import se.lexicon.flightbooking_api.repository.FlightBookingRepository;

import java.util.List;

@Component
public class FlightBookingTools {
    private Booking lastParsedBookingInfo;
    private final FlightBookingRepository flightBookingRepository;

    public FlightBookingTools(FlightBookingRepository flightBookingRepository) {
        this.flightBookingRepository = flightBookingRepository;
    }
    
    @Tool(description = "Search flight bookings by passenger e-mail")
    public List<Booking> searchByPassengerEmail(String email) {
        return flightBookingRepository.findBookingsByContactEmail(email);
    }
    
    @Tool(description = "Extract and structure booking information for database persistence")
    public String extractBookingInfo(Booking booking) {
        this.lastParsedBookingInfo = booking;
        System.out.println("Launching extractBookingInfo");
        return booking.toString();
    }
}
