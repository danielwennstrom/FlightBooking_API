package se.lexicon.flightbooking_api.service;

import com.opencsv.bean.CsvToBeanBuilder;
import jakarta.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import se.lexicon.flightbooking_api.entity.Airport;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AirportSearchServiceImpl implements AirportSearchService {
    @Value("classpath:data/airports.csv")
    private Resource airportsCsvResource;
    private final Logger logger = LoggerFactory.getLogger(AirportSearchServiceImpl.class);

    private List<Airport> airports = new ArrayList<>();
    private final Map<String, List<Airport>> iataIndex = new HashMap<>();
    private final Map<String, List<Airport>> icaoIndex = new HashMap<>();
    private final Map<String, List<Airport>> countryIndex = new HashMap<>();
    private final Map<String, List<Airport>> cityIndex = new HashMap<>();

    @PostConstruct
    public void loadAirports() {
        try {
            loadAirportsFromCsv();
            buildSearchIndices();
            logger.info("Loaded {} airports", airports.size());
        } catch (IOException e) {
            throw new RuntimeException("Failed to load airports data", e);
        }
    }

    private void loadAirportsFromCsv() throws IOException {
        try (InputStreamReader reader = new InputStreamReader(airportsCsvResource.getInputStream())) {
            airports = new CsvToBeanBuilder<Airport>(reader)
                    .withType(Airport.class)
                    .withSkipLines(1)
                    .build()
                    .parse();
        }
    }

    private void buildSearchIndices() {
        for (Airport airport : airports) {
            if (!airport.hasValidIataCode()) {
                continue;
            }
            
            if (StringUtils.hasText(airport.getIataCode())) {
                iataIndex.computeIfAbsent(airport.getIataCode().toLowerCase(), k -> new ArrayList<>())
                        .add(airport);
            }
            
            if (StringUtils.hasText(airport.getIcaoCode())) {
                icaoIndex.computeIfAbsent(airport.getIcaoCode().toLowerCase(), k -> new ArrayList<>())
                        .add(airport);
            }
            
            if (StringUtils.hasText(airport.getIsoCountry())) {
                countryIndex.computeIfAbsent(airport.getIsoCountry().toLowerCase(), k -> new ArrayList<>())
                        .add(airport);
            }
            
            if (StringUtils.hasText(airport.getMunicipality())) {
                cityIndex.computeIfAbsent(airport.getMunicipality().toLowerCase(), k -> new ArrayList<>())
                        .add(airport);
            }
        }
    }

    public List<Airport> searchAirports(String query) {
        return searchAirports(query, 10);
    }

    public List<Airport> searchAirports(String query, int limit) {
        if (!StringUtils.hasText(query)) {
            return Collections.emptyList();
        }

        String lowerQuery = query.toLowerCase().trim();
        Set<Airport> results = new LinkedHashSet<>();

        if (query.length() == 3 && iataIndex.containsKey(lowerQuery)) {
            results.addAll(iataIndex.get(lowerQuery).stream()
                    .filter(Airport::hasValidIataCode)
                    .toList());
        }

        if (query.length() == 4 && icaoIndex.containsKey(lowerQuery)) {
            results.addAll(icaoIndex.get(lowerQuery).stream()
                    .filter(Airport::hasValidIataCode)
                    .toList());
        }

        iataIndex.entrySet().stream()
                .filter(entry -> entry.getKey().startsWith(lowerQuery))
                .forEach(entry -> results.addAll(entry.getValue().stream()
                        .filter(Airport::hasValidIataCode)
                        .toList()));

        if (cityIndex.containsKey(lowerQuery)) {
            results.addAll(cityIndex.get(lowerQuery).stream()
                    .filter(Airport::hasValidIataCode)
                    .toList());
        }

        if (countryIndex.containsKey(lowerQuery)) {
            results.addAll(countryIndex.get(lowerQuery).stream()
                    .filter(Airport::hasValidIataCode)
                    .toList());
        }

        if (results.size() < limit) {
            airports.stream()
                    .filter(airport -> airport.getName() != null &&
                            airport.getName().toLowerCase().contains(lowerQuery) &&
                            airport.hasValidIataCode())
                    .forEach(results::add);
        }

        if (results.size() < limit) {
            airports.stream()
                    .filter(airport -> airport.getMunicipality() != null &&
                            airport.getMunicipality().toLowerCase().contains(lowerQuery) &&
                            airport.hasValidIataCode())
                    .forEach(results::add);
        }

        return results.stream()
                .limit(limit)
                .collect(Collectors.toList());
    }


    public List<Airport> getAirportsByCountry(String countryCode) {
        if (!StringUtils.hasText(countryCode)) {
            return Collections.emptyList();
        }
        
        return countryIndex.getOrDefault(countryCode.toLowerCase(), Collections.emptyList());
    }

    public Optional<Airport> getAirportByIataCode(String iataCode) {
        if (!StringUtils.hasText(iataCode) || iataCode.length() != 3) {
            return Optional.empty();
        }
        List<Airport> matches = iataIndex.get(iataCode.toLowerCase());
        
        return matches != null && !matches.isEmpty() ? Optional.of(matches.get(0)) : Optional.empty();
    }

    public Optional<Airport> getAirportByIcaoCode(String icaoCode) {
        if (!StringUtils.hasText(icaoCode) || icaoCode.length() != 4) {
            return Optional.empty();
        }
        List<Airport> matches = icaoIndex.get(icaoCode.toLowerCase());
        
        return matches != null && !matches.isEmpty() ? Optional.of(matches.get(0)) : Optional.empty();
    }

    public List<Airport> getAllAirports() {
        return new ArrayList<>(airports);
    }

    public List<Airport> getLimitedAirports(int limit) {
        List<Airport> filtered = airports.stream()
                .filter(Airport::hasValidIataCode)
                .collect(Collectors.toList());

        if (limit <= 0 || limit > filtered.size()) {
            throw new IllegalArgumentException("Limit must be between 1 and the number of airports with valid IATA codes");
        }

        return new ArrayList<>(filtered.subList(0, limit));
    }

    public long getAirportCount() {
        return airports.size();
    }

    public List<String> getAvailableCountries() {
        return countryIndex.keySet().stream()
                .map(String::toUpperCase)
                .map(code -> new Locale("", code).getDisplayCountry())
                .sorted()
                .collect(Collectors.toList());
    }
}
