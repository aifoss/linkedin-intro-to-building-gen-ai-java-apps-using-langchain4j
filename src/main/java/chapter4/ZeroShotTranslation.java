package chapter4;

import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.openai.OpenAiChatModel;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static util.Constants.DEFAULT_TEMPERATURE;
import static util.Constants.DEFAULT_TIMEOUT_SECS;
import static util.Constants.DEFAULT_TOKENS;
import static util.Constants.OPEN_AI_API_KEY_ENV_VAR_KEY;
import static util.Constants.OPEN_AI_CHAT_MODEL_NAME;

/**
 * 2026-02-04
 */
public class ZeroShotTranslation {

    public static void main(String[] args) {
        String apiKey = System.getenv(OPEN_AI_API_KEY_ENV_VAR_KEY);
        ChatModel chatModel = OpenAiChatModel.builder()
                .apiKey(apiKey)
                .modelName(OPEN_AI_CHAT_MODEL_NAME)
                .temperature(DEFAULT_TEMPERATURE)
                .timeout(Duration.ofSeconds(DEFAULT_TIMEOUT_SECS))
                .maxTokens(DEFAULT_TOKENS)
                .build();

        List<ChatMessage> messages = new ArrayList<>();
        messages.add(UserMessage.from("Translate the following text to German."));

        String inputText = """
                Java is a high-level object-oriented programming language designed to be platform-independent,
                meaning  code written in Java can run on any device with a Java Virtual Machine (JVM).
                """;
        messages.add(UserMessage.from(inputText));

        ChatResponse chatResponse = chatModel.chat(messages);
        String responseText = chatResponse.aiMessage().text();

        System.out.println("ENGLISH:");
        System.out.println(inputText);

        System.out.println("GERMAN:");
        System.out.println(responseText);
    }

}
