package se.lexicon.flightbooking_api.entity;

import lombok.Data;

@Data
public class ToolResponse {
    private String type;
    private String message;

    public ToolResponse(String type, String message) {
        this.type = type;
        this.message = message;
    }
}
