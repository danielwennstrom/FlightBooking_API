package se.lexicon.flightbooking_api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.lexicon.flightbooking_api.entity.FlightInfo;

import java.time.LocalDate;

@Component
public class FlightInfoParserTools {
    private final ChatClient chatClient;
    private final ObjectMapper objectMapper;
    
    @Getter
    private FlightInfo lastParsedFlightInfo;

    @Autowired
    public FlightInfoParserTools(ChatClient chatClient, ObjectMapper objectMapper) {
        this.chatClient = chatClient;
        this.objectMapper = objectMapper;
    }

    private static final String FLIGHT_PARSING_PROMPT = """
        You are a flight booking assistant. Parse the user's message and extract flight information.
        
        Return a JSON object with these fields (use null for missing information):
        {
            "departure": "airport code or city",
            "destination": "airport code or city",
            "departureDate": "YYYY-MM-DD or null",
            "returnDate": "YYYY-MM-DD or null",
            "passengers": number or null,
            "flightClass": "economy/business/first or null",
            "isRoundTrip": boolean
        }
        
        Handle natural language like:
        - "next Friday" → calculate actual date
        - "NYC" → "New York"
        - "JFK" → keep as "JFK"
        - "tomorrow" → calculate date
        - "in 2 weeks" → calculate date
        
        Today's date: {currentDate}
        
        User message: {userMessage}
        
        Return only the JSON object, no other text.
        """;

    @Tool(description = "Parses flight information from the user's message")
    public FlightInfo parseFlightInfo(String userMessage) throws JsonProcessingException {
        String prompt = FLIGHT_PARSING_PROMPT
                .replace("{currentDate}", LocalDate.now().toString())
                .replace("{userMessage}", userMessage);
        
        String response = chatClient.prompt(prompt).call().content();

        try {
            String cleanResponse = response.trim();
            if (cleanResponse.startsWith("```json")) {
                cleanResponse = cleanResponse.substring(7);
            }
            if (cleanResponse.endsWith("```")) {
                cleanResponse = cleanResponse.substring(0, cleanResponse.length() - 3);
            }
            cleanResponse = cleanResponse.trim();
            
            FlightInfo parsed = objectMapper.readValue(cleanResponse, FlightInfo.class);
            this.lastParsedFlightInfo = parsed;

            System.out.println("Last parsed flight info: " + parsed);
            return parsed;
        } catch (JsonProcessingException e) {
            throw e;
        }
    }
}
