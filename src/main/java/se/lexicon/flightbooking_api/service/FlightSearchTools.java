package se.lexicon.flightbooking_api.service;

import lombok.Getter;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;
import se.lexicon.flightbooking_api.entity.FlightInfo;
import se.lexicon.flightbooking_api.entity.flights.Flight;

import java.time.LocalDate;

@Getter
@Component
public class FlightSearchTools {
    private FlightInfo lastParsedFlightInfo;

    @Tool(description = "Extract and structure flight information for API calls and database persistence. Always parse the dates into ISO format: yyyy-MM-dd")
    public String extractFlightDetails(
            String departure,
            String destination,
            String departureDate,
            String returnDate,
            Integer passengers,
            String cabinClass,
            Boolean isRoundTrip) {

        FlightInfo flightInfo = FlightInfo.builder()
                .departure(departure)
                .destination(destination)
                .departureDate(departureDate != null ? LocalDate.parse(departureDate) : null)
                .returnDate(returnDate != null ? LocalDate.parse(returnDate) : null)
                .passengers(passengers)
                .cabinClass(cabinClass)
                .isRoundTrip(isRoundTrip)
                .build();

        this.lastParsedFlightInfo = flightInfo;
        System.out.println("Flight information confirmed: " + flightInfo.toString());

        return "I've found all the flight details you need! Here's what I have:\n" +
                flightInfo.toString() +
                "\n\nWould you like me to search for flights with these details?";
    }
}
