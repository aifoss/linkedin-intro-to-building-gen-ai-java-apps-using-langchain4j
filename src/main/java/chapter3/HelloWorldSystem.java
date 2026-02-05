package chapter3;

import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.SystemMessage;
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
public class HelloWorldSystem {

    public static void main(String[] args) {
        String apiKey = System.getenv(OPEN_AI_API_KEY_ENV_VAR_KEY);
        ChatModel chatModel = OpenAiChatModel.builder()
                .apiKey(apiKey)
                .modelName(OPEN_AI_CHAT_MODEL_NAME)
                .temperature(DEFAULT_TEMPERATURE)
                .timeout(Duration.ofSeconds(DEFAULT_TIMEOUT_SECS))
                .maxTokens(DEFAULT_TOKENS)
                .build();

        System.out.println("Hello World System -----------------------\n");

        List<ChatMessage> messages = new ArrayList<>();

        SystemMessage systemMessage = new SystemMessage(
                "You are a polite Java expert explaining concepts to a grammar school child."
        );
        messages.add(systemMessage);

        UserMessage userMessage = UserMessage.from("Why should I learn Java lambdas?");
        messages.add(userMessage);

        ChatResponse chatResponse = chatModel.chat(messages);
        String text = chatResponse.aiMessage().text();

        System.out.println(text);
    }

}
