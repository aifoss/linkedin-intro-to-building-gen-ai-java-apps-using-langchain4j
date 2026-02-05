package chapter5;

import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.input.Prompt;
import dev.langchain4j.model.input.PromptTemplate;
import util.LLMType;
import util.LLMUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 2026-02-04
 */
public class TextSummarizer {

    public static void main(String[] args) throws IOException {
        ChatModel chatModel = LLMUtil.buildChatModel(LLMType.ANTHROPIC);

        SystemMessage sysMsg = SystemMessage.from("""
                You are an expert administrator with expertise in summarizing complex texts
                """);

        List<ChatMessage> messages;

        while (true) {
            messages = new ArrayList<>();
            messages.add(sysMsg);

            String fname = LLMUtil.getUserInput("File> ");
            String level = LLMUtil.getUserInput("Level> ");
            String language = LLMUtil.getUserInput("Language> ");

            if (fname.isEmpty() || level.isEmpty() || language.isEmpty()) {
                break;
            }

            UserMessage userMsg = UserMessage.from(generatePrompt(fname, level, language));
            messages.add(userMsg);

            ChatResponse chatResponse = chatModel.chat(messages);

            System.out.println(chatResponse.aiMessage().text());
        }
    }

    public static String generatePrompt(String fileName, String summaryLevel, String language) throws IOException {
        String myTemplate = """
                Please create a summary from the following text at a {{level}} level
                using a clear, succinct paragraph that captures the essence of the text,
                highlighting key themes and insights.
                Response in {{language}}, {{file}}
                """;

        PromptTemplate promptTemplate = PromptTemplate.from(myTemplate);

        Map<String, Object> variables = new HashMap<>();
        variables.put("level", summaryLevel);
        String filePath = System.getProperty("user.dir") + "/src/main/resources/" + fileName;
        variables.put("file", new String(Files.readAllBytes(Path.of(filePath))));
        variables.put("language", language);

        Prompt prompt = promptTemplate.apply(variables);

        return prompt.text();
    }

}
