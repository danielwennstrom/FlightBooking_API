package se.lexicon.flightbooking_api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import se.lexicon.flightbooking_api.dto.*;
import se.lexicon.flightbooking_api.service.OpenAiService;

import java.io.Console;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
@Tag(name = "Flight Booking API", description = "APIs for flight booking operations")
public class ChatController {
    private final OpenAiService openAiService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public MessageDTO chat(@RequestBody MessageDTO message) throws JsonProcessingException {
        return openAiService.generateResponse(message);
    }
}