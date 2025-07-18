package se.lexicon.flightbooking_api.controller;

import org.springframework.web.bind.annotation.*;
import se.lexicon.flightbooking_api.dto.AirportDTO;
import se.lexicon.flightbooking_api.entity.Airport;
import se.lexicon.flightbooking_api.mapper.AirportMapper;
import se.lexicon.flightbooking_api.service.AirportSearchService;

import java.util.List;

@RestController
@RequestMapping("/api/airports")
public class AirportController {
    private final AirportSearchService airportSearchService;
    private final AirportMapper airportMapper;

    public AirportController(AirportSearchService airportSearchService, AirportMapper airportMapper) {
        this.airportSearchService = airportSearchService;
        this.airportMapper = airportMapper;
    }

    @GetMapping("/{limit}")
    public List<AirportDTO> getLimitedAirports(@PathVariable int limit) {
        return airportMapper.toDTOList(airportSearchService.getLimitedAirports(limit));
    }
    
    @GetMapping("/search/")
    public List<AirportDTO> searchAirports(@RequestParam String query) {
        return airportMapper.toDTOList(airportSearchService.searchAirports(query));
    }
}
