package se.lexicon.flightbooking_api.dto;

import lombok.Data;
import se.lexicon.flightbooking_api.entity.ToolResponse;
import se.lexicon.flightbooking_api.enums.Sender;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
public class MessageDTO {
    private UUID id;
    private String content;
    private Sender sender;
    private LocalDateTime timestamp;
    private List<ToolResponse> toolResponses;
    private boolean fullWidth;
    
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
    
    public void addToolResponse(ToolResponse toolResponse) {
        if (toolResponses == null)
            toolResponses = new ArrayList<>();
        toolResponses.add(toolResponse);
    }
}
