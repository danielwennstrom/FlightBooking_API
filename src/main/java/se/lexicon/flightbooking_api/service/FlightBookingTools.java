package se.lexicon.flightbooking_api.service;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;
import se.lexicon.flightbooking_api.entity.booking.Booking;
import se.lexicon.flightbooking_api.entity.booking.Passenger;
import se.lexicon.flightbooking_api.repository.FlightBookingRepository;

import java.util.List;
import java.util.Map;

@Component
public class FlightBookingTools {
    private Booking lastParsedBookingInfo;
    private final FlightBookingRepository flightBookingRepository;

    public FlightBookingTools(FlightBookingRepository flightBookingRepository) {
        this.flightBookingRepository = flightBookingRepository;
    }

    @Tool(description = "Search flight bookings by passenger e-mail")
    public List<Booking> searchByPassengerEmail(String email) {
        System.out.println("Launching searchByPassengerEmail");
        return flightBookingRepository.findBookingsByContactEmail(email);
    }

    @Tool(description = "Extract and structure booking info including passengers")
    public String extractBookingInfo(
            Booking booking,
            List<Passenger> passengerDataList
    ) {
        System.out.println("Launching extractBookingInfo");
        System.out.println(booking);
        System.out.println(passengerDataList);
        
        booking.getPassengerList().clear();

        for (Passenger passengerData : passengerDataList) {
            Passenger passenger = new Passenger();
            passenger.setPassengerName(passengerData.getPassengerName());
            passenger.setPassengerEmail(passengerData.getPassengerEmail());
            booking.addPassenger(passenger);
        }

        this.lastParsedBookingInfo = booking;
        System.out.println("Extracted booking info: " + booking);

        return booking.toString();
    }


    @Tool(description = "Book the flight with booking info and passenger details, then save to database")
    public Booking bookFlight(Booking bookingInfo, List<Passenger> passengerDataList) {
        System.out.println("Launching bookFlight");
        System.out.println(bookingInfo);
        System.out.println(passengerDataList);
        
        bookingInfo.getPassengerList().clear();
        for (Passenger passengerData : passengerDataList) {
            Passenger passenger = new Passenger();
            passenger.setPassengerName(passengerData.getPassengerName());
            passenger.setPassengerEmail(passengerData.getPassengerEmail());
            bookingInfo.addPassenger(passenger);
        }

        Booking savedBooking = flightBookingRepository.save(bookingInfo);

        System.out.println("Booking saved: " + savedBooking);
        return savedBooking;
    }
    
    @Tool(description = "Cancels the flight booking using the passenger's contact email")
    public String cancelFlight(Booking booking) {
        flightBookingRepository.delete(booking);
        return "Booking deleted";
    }

    @Tool(description = "Build the final booking confirmation message with relevant information to the user")
    public String buildBookingConfirmation(Booking booking) {
        StringBuilder sb = new StringBuilder();
        sb.append("Booking successful! 🎉\n");
        sb.append("Booking ID: ").append(booking.getId()).append("\n");
        sb.append("Flight: ").append(booking.getDeparture())
                .append(" to ").append(booking.getDestination()).append("\n");
        sb.append("Departure date: ").append(booking.getDepartureDate().toLocalDate()).append("\n");
        if (booking.isRoundTrip()) {
            sb.append("Return date: ").append(booking.getReturnDate().toLocalDate()).append("\n");
        }
        sb.append("Passengers:\n");
        for (Passenger p : booking.getPassengerList()) {
            sb.append("- ").append(p.getPassengerName()).append("\n");
        }
        sb.append("Confirmation email sent to: ").append(booking.getContactEmail()).append("\n");
        sb.append("Total price: ").append(booking.getTotalPrice()).append(" ").append(booking.getCurrency());

        return sb.toString();
    }

}
