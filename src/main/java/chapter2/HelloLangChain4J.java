package chapter2;

import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;

import static util.Constants.OPEN_AI_API_KEY_ENV_VAR_KEY;
import static util.Constants.OPEN_AI_CHAT_MODEL_NAME;

/**
 * 2026-02-03
 */
public class HelloLangChain4J {

    public static void main(String[] args) {
        String apiKey = System.getenv(OPEN_AI_API_KEY_ENV_VAR_KEY);

        ChatModel chatModel = OpenAiChatModel.builder()
                .apiKey(apiKey)
                .modelName(OPEN_AI_CHAT_MODEL_NAME)
                .build();

        String msg = "Why should I learn Java?";
        ChatMessage userMessage = UserMessage.from(msg);

        var chatResponse = chatModel.chat(userMessage);
        var text = chatResponse.aiMessage().text();

        System.out.println(text);
    }

}
