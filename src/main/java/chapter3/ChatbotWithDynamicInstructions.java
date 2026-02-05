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
import java.util.Scanner;

import static util.Constants.CHATBOT_STOPWORD;
import static util.Constants.DEFAULT_TEMPERATURE;
import static util.Constants.DEFAULT_TIMEOUT_SECS;
import static util.Constants.DEFAULT_TOKENS;
import static util.Constants.INSTRUCTION_PROMPT;
import static util.Constants.OPEN_AI_API_KEY_ENV_VAR_KEY;
import static util.Constants.OPEN_AI_CHAT_MODEL_NAME;
import static util.Constants.QUESTION_PROMPT;

/**
 * 2026-02-04
 */
public class ChatbotWithDynamicInstructions {

    public static void main(String[] args) {
        String apiKey = System.getenv(OPEN_AI_API_KEY_ENV_VAR_KEY);
        ChatModel chatModel = OpenAiChatModel.builder()
                .apiKey(apiKey)
                .modelName(OPEN_AI_CHAT_MODEL_NAME)
                .temperature(DEFAULT_TEMPERATURE)
                .timeout(Duration.ofSeconds(DEFAULT_TIMEOUT_SECS))
                .maxTokens(DEFAULT_TOKENS)
                .build();

        Scanner in = new Scanner(System.in);
        String userInput;

        List<ChatMessage> messages;

        while (true) {
            messages = new ArrayList<>();

            System.out.print(INSTRUCTION_PROMPT);
            userInput = in.nextLine();

            if (userInput.equalsIgnoreCase(CHATBOT_STOPWORD)) {
                break;
            } else if (!userInput.isBlank()) {
                SystemMessage systemMessage = new SystemMessage(userInput);
                messages.add(systemMessage);
            } else {
                continue;
            }

            System.out.print(QUESTION_PROMPT);
            userInput = in.nextLine();

            if (userInput.equalsIgnoreCase(CHATBOT_STOPWORD)) {
                break;
            } else if (!userInput.isBlank()) {
                UserMessage userMessage = UserMessage.from(userInput);
                messages.add(userMessage);
            } else {
                continue;
            }

            ChatResponse chatResponse = chatModel.chat(messages);
            String text = chatResponse.aiMessage().text();

            System.out.println(text);
        }

        in.close();
    }

}
