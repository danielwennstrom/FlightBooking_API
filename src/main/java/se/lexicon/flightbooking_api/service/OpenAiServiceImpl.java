package se.lexicon.flightbooking_api.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Service;
import se.lexicon.flightbooking_api.config.OpenAiConfig;
import se.lexicon.flightbooking_api.dto.MessageDTO;
import se.lexicon.flightbooking_api.entity.ToolResponse;

import java.time.LocalDate;

@Service
public class OpenAiServiceImpl implements OpenAiService {
    private final ChatClient chatClient;
    private final ChatMemory chatMemory;
    private final OpenAiConfig openAiConfig;
    private final FlightSearchTools flightSearchTools;
    private final VisualToolResponseTools visualToolResponseTools;

    public OpenAiServiceImpl(ChatClient.Builder chatClientBuilder, ChatMemory chatMemory, OpenAiConfig openAiConfig, FlightSearchTools flightSearchTools, VisualToolResponseTools visualToolResponseTools) {
        this.openAiConfig = openAiConfig;
        this.flightSearchTools = flightSearchTools;
        this.visualToolResponseTools = visualToolResponseTools;
        this.chatClient = chatClientBuilder.defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build()).build();
        this.chatMemory = chatMemory;
    }

    @Override
    public MessageDTO generateResponse(MessageDTO message) {
        SystemMessage systemMessage = SystemMessage.builder()
                .text(openAiConfig.getSystemPrompt().replace("{currentDate}", LocalDate.now().toString())).build();

        UserMessage userMessage = UserMessage.builder()
                .text(message.getContent())
                .build();

        Prompt prompt = Prompt.builder().messages(systemMessage, userMessage).build();
        ChatResponse response = chatClient.prompt(prompt)
                .advisors(a -> a.param(ChatMemory.CONVERSATION_ID, message.getId()))
                .tools(flightSearchTools, visualToolResponseTools)
                .call()
                .chatResponse();

        assert response != null;
        String responseText = response.getResult().getOutput().getText();

        MessageDTO botResponse = MessageDTO.fromBot(responseText);
        System.out.println(responseText);
        botResponse.setFlightInfo(flightSearchTools.getLastParsedFlightInfo());
        System.out.println(flightSearchTools.getLastParsedFlightInfo());

        assert responseText != null;
        if (responseText.contains("[LAUNCH_DATE_PICKER]")) {
            botResponse.addToolResponse(new ToolResponse("DATE_PICKER", null));
            botResponse.setContent(botResponse.getContent().replace("[LAUNCH_DATE_PICKER]", ""));
        }

        if (responseText.contains("[LAUNCH_DESTINATION_PICKER]")) {
            botResponse.addToolResponse(new ToolResponse("DESTINATION_PICKER", null));
            botResponse.setContent(botResponse.getContent().replace("[LAUNCH_DESTINATION_PICKER]", ""));
        }

        if (responseText.contains("[LAUNCH_FLIGHT_PICKER]")) {
            botResponse.addToolResponse(new ToolResponse("FLIGHT_PICKER", null));
            botResponse.setFullWidth(true);
            botResponse.setContent(botResponse.getContent().replace("[LAUNCH_FLIGHT_PICKER]", ""));
        }
        
        return botResponse;
    }
}
