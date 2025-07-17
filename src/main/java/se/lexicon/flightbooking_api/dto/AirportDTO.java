package se.lexicon.flightbooking_api.dto;

import lombok.Data;

@Data
public class AirportDTO {
    private String iataCode;
    private String icaoCode;
    private String name;
    private String isoCountry;
    private String countryName;
    private String municipality;
    private String displayName;

    public AirportDTO(String iataCode, String icaoCode, String name, String isoCountry, String countryName, String municipality, String displayName) {
        this.iataCode = iataCode;
        this.icaoCode = icaoCode;
        this.name = name;
        this.isoCountry = isoCountry;
        this.countryName = countryName;
        this.municipality = municipality;
        this.displayName = displayName;
    }
}
