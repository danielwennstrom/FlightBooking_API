package se.lexicon.flightbooking_api.service;

import se.lexicon.flightbooking_api.dto.MessageDTO;

public interface OpenAiService {
    public MessageDTO generateResponse(MessageDTO message);
}
