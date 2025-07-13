package se.lexicon.flightbooking_api.dto;

import lombok.Data;
import se.lexicon.flightbooking_api.enums.Sender;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Data
public class MessageDTO {
    public UUID id;
    public String content;
    public Sender sender;
    public LocalDateTime timestamp;
    
    public MessageDTO(String content, Sender sender)
    {
        this.id = UUID.randomUUID();
        this.content = content;
        this.sender = sender;
        this.timestamp = LocalDateTime.now();
    }

    public static MessageDTO fromUser(String content) {
        return new MessageDTO(content, Sender.USER);
    }

    public static MessageDTO fromBot(String content) {
        return new MessageDTO(content, Sender.BOT);
    }
}
