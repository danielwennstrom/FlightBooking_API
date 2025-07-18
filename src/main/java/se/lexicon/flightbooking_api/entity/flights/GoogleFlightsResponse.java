package se.lexicon.flightbooking_api.entity.flights;


import lombok.Getter;

@Getter
public class GoogleFlightsResponse {
    private boolean status;
    private String message;
    private long timestamp;
    private Data data;
}
