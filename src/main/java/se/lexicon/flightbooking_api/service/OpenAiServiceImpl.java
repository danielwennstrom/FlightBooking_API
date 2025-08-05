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
import se.lexicon.flightbooking_api.dto.flights.FlightDTO;
import se.lexicon.flightbooking_api.entity.ToolResponse;

import java.time.LocalDate;
import java.util.List;

@Service
public class OpenAiServiceImpl implements OpenAiService {
    private final ChatClient chatClient;
    private final ChatMemory chatMemory;
    private final OpenAiConfig openAiConfig;
    private final FlightSearchTools flightSearchTools;
    private final VisualToolResponseTools visualToolResponseTools;
    private final FlightBookingTools flightBookingTools;

    public OpenAiServiceImpl(ChatClient.Builder chatClientBuilder, ChatMemory chatMemory, OpenAiConfig openAiConfig, FlightSearchTools flightSearchTools, VisualToolResponseTools visualToolResponseTools, FlightBookingTools flightBookingTools) {
        this.openAiConfig = openAiConfig;
        this.flightSearchTools = flightSearchTools;
        this.visualToolResponseTools = visualToolResponseTools;
        this.flightBookingTools = flightBookingTools;
        this.chatClient = chatClientBuilder.defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build()).build();
        this.chatMemory = chatMemory;
    }

    @Override
    public MessageDTO generateResponse(MessageDTO message) {
        SystemMessage systemMessage = SystemMessage.builder()
                .text(openAiConfig.getSystemPrompt().replace("{currentDate}", LocalDate.now().toString())).build();
        StringBuilder sb = new StringBuilder();
        sb.append(message.getContent()).append("\n\n");

        List<FlightDTO> flightDTOS = null;

        if (message.getToolResponses() != null && message.getToolResponses().stream().findFirst().isPresent()) {
            flightDTOS = message.getToolResponses().stream().findFirst().get().getFlightData();
        }

        if (flightDTOS != null && !flightDTOS.isEmpty()) {
            sb.append(flightDTOS);
        }

        UserMessage userMessage = UserMessage.builder()
                .text(sb.toString())
                .build();

        Prompt prompt = Prompt.builder().messages(systemMessage, userMessage).build();
        ChatResponse response = chatClient.prompt(prompt)
                .advisors(a -> a.param(ChatMemory.CONVERSATION_ID, message.getId()))
                .tools(flightSearchTools, visualToolResponseTools, flightBookingTools)
                .call()
                .chatResponse();

        assert response != null;
        String responseText = response.getResult().getOutput().getText();

        MessageDTO botResponse = MessageDTO.fromBot(responseText);
        botResponse.setFlightInfo(flightSearchTools.getLastParsedFlightInfo());

        // the frontend relies on these ToolResponses to render the visual tools, and the backend relies on these strings
        // because getting the ToolCalls from the ChatResponse didn't seem to work
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
