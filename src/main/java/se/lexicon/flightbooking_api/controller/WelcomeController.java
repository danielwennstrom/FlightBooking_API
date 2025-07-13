package se.lexicon.flightbooking_api.controller;

import org.aspectj.bridge.Message;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.lexicon.flightbooking_api.dto.MessageDTO;
import se.lexicon.flightbooking_api.enums.Sender;

@RestController
@RequestMapping("api/welcome")
public class WelcomeController {
    @PostMapping
    public MessageDTO sayHello() {
        return MessageDTO.fromBot("Welcome! I am FlyMate, your friendly flight booking assistant. How can I help you?");
    }
}
