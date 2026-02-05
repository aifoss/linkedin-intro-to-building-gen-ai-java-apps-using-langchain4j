package chapter4;

import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.response.ChatResponse;
import util.LLMType;
import util.LLMUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 2026-02-04
 */
public class SamePromptToMultipleLLMs {

    public static void main(String[] args) {
        ChatModel chatModel = LLMUtil.buildChatModel(LLMType.OPEN_AI);

        List<ChatMessage> context = new ArrayList<>();
        SystemMessage systemMessage = new SystemMessage("Response concisely in one sentence.");
        context.add(systemMessage);
        UserMessage userMessage = UserMessage.from("What are the most significant feature of the latest Java version?");
        context.add(userMessage);

        ChatResponse chatResponse = LLMUtil.sendChatMessages(chatModel, context);
        String responseText = chatResponse.aiMessage().text();
        context.add(UserMessage.from(responseText));

        System.out.println("1: OPEN AI:");
        System.out.println(responseText);

        chatModel = LLMUtil.buildChatModel(LLMType.ANTHROPIC);

        systemMessage = new SystemMessage("Elaborate the previous completion in two sentences.");
        context.add(systemMessage);

        chatResponse = LLMUtil.sendChatMessages(chatModel, context);
        responseText = chatResponse.aiMessage().text();

        System.out.println("2: ANTHROPIC:");
        System.out.println(responseText);
    }

}
