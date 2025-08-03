package se.lexicon.flightbooking_api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;
import reactor.core.publisher.Mono;
import se.lexicon.flightbooking_api.dto.flights.ItinerariesDTO;
import se.lexicon.flightbooking_api.entity.FlightInfo;
import se.lexicon.flightbooking_api.entity.flights.GoogleFlightsResponse;
import se.lexicon.flightbooking_api.mapper.ItinerariesMapper;

import java.util.Map;

@RestController
@RequestMapping("api/flights")
public class FlightSearchController {
    @Value("classpath:data/departures.json")
    private Resource departures;
    @Value("classpath:data/returns.json")
    private Resource returns;
    private final ObjectMapper objectMapper;
    private final ItinerariesMapper itinerariesMapper;
    private final WebClient webClient;
    private final String rapidApiKey = System.getProperty("RAPIDAPI_API_KEY");

    public FlightSearchController(ObjectMapper objectMapper, ItinerariesMapper itinerariesMapper, WebClient webClient) {
        this.objectMapper = objectMapper;
        this.itinerariesMapper = itinerariesMapper;
        this.webClient = webClient;
    }

    @PostMapping("/search")
    public Mono<ItinerariesDTO> searchFlights(@RequestBody FlightInfo flightInfo) {
        return webClient.get()
                .uri(uriBuilder -> {
                    UriBuilder builder = uriBuilder
                            .scheme("https")
                            .host("google-flights2.p.rapidapi.com")
                            .path("/api/v1/searchFlights")
                            .queryParam("departure_id", flightInfo.getDeparture())
                            .queryParam("arrival_id", flightInfo.getDestination())
                            .queryParam("travel_class", flightInfo.getCabinClass())
                            .queryParam("adults", flightInfo.getPassengers())
                            .queryParam("outbound_date", flightInfo.getDepartureDate());

                    if (flightInfo.getIsRoundTrip()) {
                        builder.queryParam("return_date", flightInfo.getReturnDate());
                    }

                    return builder
                            .queryParam("show_hidden", 1)
                            .queryParam("currency", "USD")
                            .queryParam("language_code", "en-US")
                            .queryParam("country_code", "US")
                            .queryParam("search_type", "best")
                            .build();
                })
                .header("x-rapidapi-host", "google-flights2.p.rapidapi.com")
                .header("x-rapidapi-key", rapidApiKey)
                .retrieve()
                //                .bodyToMono(String.class)
                .bodyToMono(GoogleFlightsResponse.class)
//                .doOnNext(json -> System.out.println("Response: " + json))
//                .block();
                .map(response -> itinerariesMapper.toDto(response.getData().getItineraries()));
    }

    @PostMapping("/searchNext")
    public Mono<ItinerariesDTO> searchReturnFlights(@RequestBody Map<String, String> request) {
        return webClient.get().uri(uriBuilder -> uriBuilder.scheme("https").host("google-flights2.p.rapidapi.com").path("/api/v1/getNextFlights").queryParam("next_token", request.get("nextToken")).build()).header("x-rapidapi-host", "google-flights2.p.rapidapi.com").header("x-rapidapi-key", rapidApiKey).retrieve().bodyToMono(GoogleFlightsResponse.class).map(response -> itinerariesMapper.toDto(response.getData().getItineraries()));
    }

//    @PostMapping("/search")
//    public ItinerariesDTO searchFlights(@RequestBody FlightInfo flightInfo) throws IOException {
//        GoogleFlightsResponse response = objectMapper.readValue(departures.getInputStream(), GoogleFlightsResponse.class);
//        ItinerariesDTO dto = itinerariesMapper.toDto(response.getData().getItineraries());
//        return dto;
//    }
//
//    @PostMapping("/searchNext")
//    public ItinerariesDTO searchReturnFlights(@RequestBody FlightInfo flightInfo) throws IOException {
//        GoogleFlightsResponse response = objectMapper.readValue(returns.getInputStream(), GoogleFlightsResponse.class);
//        ItinerariesDTO dto = itinerariesMapper.toDto(response.getData().getItineraries());
//        return dto;
//    }
}
