package chapter2;

import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.openai.OpenAiChatModel;

import java.util.Scanner;

import static util.Constants.CHATBOT_STOPWORD;
import static util.Constants.OPEN_AI_API_KEY_ENV_VAR_KEY;
import static util.Constants.OPEN_AI_CHAT_MODEL_NAME;
import static util.Constants.PROMPT;

/**
 * 2026-02-03
 */
public class SimpleChatbot {

    public static void main(String[] args) {
        String apiKey = System.getenv(OPEN_AI_API_KEY_ENV_VAR_KEY);
        ChatModel chatModel = OpenAiChatModel.builder()
                .apiKey(apiKey)
                .modelName(OPEN_AI_CHAT_MODEL_NAME)
                .build();

        Scanner in = new Scanner(System.in);

        while (true) {
            System.out.print(PROMPT);

            String userInput = in.nextLine();

            if (userInput.isBlank()) {
                continue;
            } else if (userInput.equals(CHATBOT_STOPWORD)) {
                break;
            }

            ChatMessage userMessage = UserMessage.from(userInput);
            ChatResponse chatResponse = chatModel.chat(userMessage);

            System.out.println(chatResponse.aiMessage().text());
        }

        in.close();
    }

}
