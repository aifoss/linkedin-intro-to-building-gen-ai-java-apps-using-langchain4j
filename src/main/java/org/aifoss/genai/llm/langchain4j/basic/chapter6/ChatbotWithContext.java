package org.aifoss.genai.llm.langchain4j.basic.chapter6;

import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.response.ChatResponse;
import org.aifoss.genai.llm.langchain4j.basic.util.LLMType;
import org.aifoss.genai.llm.langchain4j.basic.util.LLMUtil;

import java.util.Scanner;

import static org.aifoss.genai.llm.langchain4j.basic.util.Constants.CHATBOT_STOPWORD;
import static org.aifoss.genai.llm.langchain4j.basic.util.Constants.DEFAULT_CHAT_MEMORY_SIZE;
import static org.aifoss.genai.llm.langchain4j.basic.util.Constants.PROMPT;

/**
 * Sofia
 * 2026-02-04
 */
public class ChatbotWithContext {

    public static void main(String[] args) {
        ChatModel chatModel = LLMUtil.buildChatModel(LLMType.OPEN_AI);
        ChatMemory chatMemory = MessageWindowChatMemory.withMaxMessages(DEFAULT_CHAT_MEMORY_SIZE);

        SystemMessage sysMsg = new SystemMessage(
                """
                        You are a polite software consultant with deep expertise in AI and Machine Learning.,
                        """
        );
        chatMemory.add(sysMsg);

        Scanner in;
        String userInput;

        while (true) {
            System.out.print(PROMPT);

            in = new Scanner(System.in);
            userInput = in.nextLine();

            if (userInput.isBlank()) {
                continue;
            } else if (userInput.equalsIgnoreCase(CHATBOT_STOPWORD)) {
                break;
            }

            UserMessage usrMsg = UserMessage.from(userInput);
            chatMemory.add(usrMsg);

            ChatResponse chatResponse = chatModel.chat(chatMemory.messages());
            String responseText = chatResponse.aiMessage().text();

            System.out.println(responseText);

            chatMemory.add(UserMessage.from(responseText));
        }
    }

}
