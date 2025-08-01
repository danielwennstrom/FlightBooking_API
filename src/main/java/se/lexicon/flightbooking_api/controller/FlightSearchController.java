package se.lexicon.flightbooking_api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;
import se.lexicon.flightbooking_api.dto.flights.GoogleFlightsResponseDTO;
import se.lexicon.flightbooking_api.dto.flights.ItinerariesDTO;
import se.lexicon.flightbooking_api.entity.FlightInfo;
import se.lexicon.flightbooking_api.entity.flights.GoogleFlightsResponse;
import se.lexicon.flightbooking_api.mapper.GoogleFlightsResponseMapper;
import se.lexicon.flightbooking_api.mapper.ItinerariesMapper;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("api/flights")
public class FlightSearchController {
    @Value("classpath:data/temp.json")
    private Resource departures;
    @Value("classpath:data/temp2.json")
    private Resource returns;
    private final ObjectMapper objectMapper;
    private final GoogleFlightsResponseMapper googleFlightsResponseMapper;
    private final ItinerariesMapper itinerariesMapper;

    public FlightSearchController(ObjectMapper objectMapper, GoogleFlightsResponseMapper googleFlightsResponseMapper, ItinerariesMapper itinerariesMapper) {
        this.objectMapper = objectMapper;
        this.googleFlightsResponseMapper = googleFlightsResponseMapper;
        this.itinerariesMapper = itinerariesMapper;
    }

    @PostMapping("/search")
    public ItinerariesDTO searchFlights(@RequestBody FlightInfo flightInfo) throws IOException {
        GoogleFlightsResponse response = objectMapper.readValue(departures.getInputStream(), GoogleFlightsResponse.class);
        ItinerariesDTO dto = itinerariesMapper.toDto(response.getData().getItineraries());
        return dto;
    }

    @PostMapping("/searchNext")
    public ItinerariesDTO searchReturnFlights(@RequestBody Map<String, String> request) throws IOException {
        GoogleFlightsResponse response = objectMapper.readValue(returns.getInputStream(), GoogleFlightsResponse.class);
        ItinerariesDTO dto = itinerariesMapper.toDto(response.getData().getItineraries());
        return dto;
    }
}
