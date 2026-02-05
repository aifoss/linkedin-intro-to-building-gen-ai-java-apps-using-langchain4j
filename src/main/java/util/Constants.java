package util;

/**
 * 2026-02-03
 */
public class Constants {

    public static final String PROMPT = "prompt> ";
    public static final String INSTRUCTION_PROMPT = "Instruction> ";
    public static final String QUESTION_PROMPT = "Question> ";

    public static final String CHATBOT_STOPWORD = "STOP";

    public static final String OPEN_AI_API_KEY_ENV_VAR_KEY = "OPEN_AI_API_KEY";
    public static final String OPEN_AI_CHAT_MODEL_NAME = "gpt-4o-2024-11-20";

    public static final String ANTHROPIC_API_KEY_ENV_VAR_KEY = "ANTHROPIC_API_KEY";
    public static final String ANTHROPIC_CHAT_MODEL_NAME = "claude-haiku-4-5-20251001";

    public static final double DEFAULT_TEMPERATURE = 0.3;
    public static final int DEFAULT_TIMEOUT_SECS = 120;
    public static final int DEFAULT_TOKENS = 512;

    public static final int DEFAULT_CHAT_MEMORY_SIZE = 100;

}
