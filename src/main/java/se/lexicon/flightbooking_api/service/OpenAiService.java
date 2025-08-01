package se.lexicon.flightbooking_api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import se.lexicon.flightbooking_api.dto.MessageDTO;

public interface OpenAiService {
    public MessageDTO generateResponse(MessageDTO message);
}
