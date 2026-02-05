package chapter5;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.input.Prompt;
import dev.langchain4j.model.input.PromptTemplate;
import util.LLMType;
import util.LLMUtil;

import java.util.HashMap;
import java.util.Map;

import static util.Constants.PROMPT;

/**
 * 2026-02-04
 */
public class SimplePromptTemplate {

    public static void main(String[] args) {
        ChatModel chatModel = LLMUtil.buildChatModel(LLMType.OPEN_AI);

        String myTemplate = "Please explain {{topic}} to a {{student_type}} using a clear, succinct paragraph.\n";
        PromptTemplate promptTemplate = PromptTemplate.from(myTemplate);

        Map<String, Object> variables = new HashMap<>();
        variables.put("topic", "baking a cake");
        variables.put("student_type", "java programmer");

        Prompt prompt = promptTemplate.apply(variables);
        String promptText = prompt.text();

        System.out.print(PROMPT);
        System.out.println(promptText);

        String response = chatModel.chat(promptText);

        System.out.println(response);
    }

}
