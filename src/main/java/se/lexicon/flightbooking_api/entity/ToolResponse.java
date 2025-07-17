package se.lexicon.flightbooking_api.entity;

import lombok.Data;

@Data
public class ToolResponse {
    private String type;
    private String message;
    private FlightInfo data;

    public ToolResponse(String type, FlightInfo data, String message) {
        this.type = type;
        this.data = data;
        this.message = message;
    }
}
