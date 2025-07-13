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

@Service
public class OpenAiServiceImpl implements OpenAiService {
    private final ChatClient chatClient;
    private final ChatMemory chatMemory;
    private final OpenAiConfig openAiConfig;
    
    public OpenAiServiceImpl(ChatClient.Builder chatClientBuilder, ChatMemory chatMemory, OpenAiConfig openAiConfig) {
        this.openAiConfig = openAiConfig;
        this.chatClient = chatClientBuilder.defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build()).build();
        this.chatMemory = chatMemory;
    }

    @Override
    public MessageDTO generateResponse(MessageDTO message) {
        SystemMessage systemMessage = SystemMessage.builder()
                .text(openAiConfig.getSystemPrompt()).build();

        UserMessage userMessage = UserMessage.builder()
                .text(message.getContent())
                .build();

        Prompt prompt = Prompt.builder().messages(systemMessage, userMessage).build();
        ChatResponse response = chatClient.prompt(prompt)
                .advisors(a -> a.param(ChatMemory.CONVERSATION_ID, message.getId()))
                .call()
                .chatResponse();

        assert response != null;
        return MessageDTO.fromBot(response.getResult().getOutput().getText());
    }
}
