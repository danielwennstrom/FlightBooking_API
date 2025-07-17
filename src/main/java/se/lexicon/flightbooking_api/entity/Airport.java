package se.lexicon.flightbooking_api.entity;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import lombok.Data;

@Data
public class Airport {
    @CsvBindByName(column = "id")
    @CsvBindByPosition(position = 0)
    private String id;

    @CsvBindByName(column = "ident")
    @CsvBindByPosition(position = 1)
    private String ident;

    @CsvBindByName(column = "type")
    @CsvBindByPosition(position = 2)
    private String type;

    @CsvBindByName(column = "name")
    @CsvBindByPosition(position = 3)
    private String name;

    @CsvBindByName(column = "latitude_deg")
    @CsvBindByPosition(position = 4)
    private String latitudeDeg;

    @CsvBindByName(column = "longitude_deg")
    @CsvBindByPosition(position = 5)
    private String longitudeDeg;

    @CsvBindByName(column = "elevation_ft")
    @CsvBindByPosition(position = 6)
    private String elevationFt;

    @CsvBindByName(column = "continent")
    @CsvBindByPosition(position = 7)
    private String continent;

    @CsvBindByName(column = "iso_country")
    @CsvBindByPosition(position = 8)
    private String isoCountry;

    @CsvBindByName(column = "iso_region")
    @CsvBindByPosition(position = 9)
    private String isoRegion;

    @CsvBindByName(column = "municipality")
    @CsvBindByPosition(position = 10)
    private String municipality;

    @CsvBindByName(column = "scheduled_service")
    @CsvBindByPosition(position = 11)
    private String scheduledService;

    @CsvBindByName(column = "icao_code")
    @CsvBindByPosition(position = 12)
    private String icaoCode;

    @CsvBindByName(column = "iata_code")
    @CsvBindByPosition(position = 13)
    private String iataCode;

    @CsvBindByName(column = "gps_code")
    @CsvBindByPosition(position = 14)
    private String gpsCode;

    @CsvBindByName(column = "local_code")
    @CsvBindByPosition(position = 15)
    private String localCode;

    @CsvBindByName(column = "home_link")
    @CsvBindByPosition(position = 16)
    private String homeLink;

    @CsvBindByName(column = "wikipedia_link")
    @CsvBindByPosition(position = 17)
    private String wikipediaLink;

    @CsvBindByName(column = "keywords")
    @CsvBindByPosition(position = 18)
    private String keywords;
    
    public Airport() {}
    
    public Airport(String id, String name, String iataCode, String icaoCode,
                   String isoCountry, String municipality, String latitudeDeg, String longitudeDeg) {
        this.id = id;
        this.name = name;
        this.iataCode = iataCode;
        this.icaoCode = icaoCode;
        this.isoCountry = isoCountry;
        this.municipality = municipality;
        this.latitudeDeg = latitudeDeg;
        this.longitudeDeg = longitudeDeg;
    }
    
    public boolean hasValidIataCode() {
        return iataCode != null && !iataCode.trim().isEmpty();
    }

    public String getDisplayName() {
        StringBuilder display = new StringBuilder();
        if (name != null) display.append(name);
        if (municipality != null && !municipality.trim().isEmpty()) {
            display.append(" (").append(municipality).append(")");
        }
        if (hasValidIataCode()) {
            display.append(" - ").append(iataCode);
        }
        return display.toString();
    }
    
    public String getCountryName() {
        if (isoCountry == null) return null;
        return new java.util.Locale("", isoCountry).getDisplayCountry();
    }
}