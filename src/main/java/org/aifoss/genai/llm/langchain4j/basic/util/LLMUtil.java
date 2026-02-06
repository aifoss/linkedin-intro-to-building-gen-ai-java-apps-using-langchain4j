package org.aifoss.genai.llm.langchain4j.basic.util;

import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.model.anthropic.AnthropicChatModel;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.openai.OpenAiChatModel;

import java.time.Duration;
import java.util.List;
import java.util.Scanner;

import static org.aifoss.genai.llm.langchain4j.basic.util.Constants.ANTHROPIC_API_KEY_ENV_VAR_KEY;
import static org.aifoss.genai.llm.langchain4j.basic.util.Constants.ANTHROPIC_CHAT_MODEL_NAME;
import static org.aifoss.genai.llm.langchain4j.basic.util.Constants.DEFAULT_TEMPERATURE;
import static org.aifoss.genai.llm.langchain4j.basic.util.Constants.DEFAULT_TIMEOUT_SECS;
import static org.aifoss.genai.llm.langchain4j.basic.util.Constants.DEFAULT_TOKENS;
import static org.aifoss.genai.llm.langchain4j.basic.util.Constants.OPEN_AI_API_KEY_ENV_VAR_KEY;
import static org.aifoss.genai.llm.langchain4j.basic.util.Constants.OPEN_AI_CHAT_MODEL_NAME;

/**
 * Sofia
 * 2026-02-04
 */
public class LLMUtil {

    public static ChatModel buildChatModel(LLMType llmType) {
        switch(llmType) {
            case OPEN_AI -> {
                String apiKey = System.getenv(OPEN_AI_API_KEY_ENV_VAR_KEY);
                ChatModel chatModel = OpenAiChatModel.builder()
                        .apiKey(apiKey)
                        .modelName(OPEN_AI_CHAT_MODEL_NAME)
                        .temperature(DEFAULT_TEMPERATURE)
                        .timeout(Duration.ofSeconds(DEFAULT_TIMEOUT_SECS))
                        .maxTokens(DEFAULT_TOKENS)
                        .build();
                return chatModel;
            }
            case ANTHROPIC -> {
                String apiKey = System.getenv(ANTHROPIC_API_KEY_ENV_VAR_KEY);
                ChatModel chatModel = AnthropicChatModel.builder()
                        .apiKey(apiKey)
                        .modelName(ANTHROPIC_CHAT_MODEL_NAME)
                        .build();
                return chatModel;
            }
            default -> {
                throw new RuntimeException("Unrecognized LLM type!");
            }
        }
    }

    public static ChatResponse sendChatMessages(ChatModel chatModel, List<ChatMessage> chatMessages) {
        return chatModel.chat(chatMessages);
    }

    public static String getUserInput(String prompt) {
        System.out.print(prompt);
        Scanner in = new Scanner(System.in);
        String userInput = in.nextLine();
        return userInput;
    }

}
