package com.javarush.telegram;

import java.util.ArrayList;
import java.util.List;

public final class TelegramBotContext {

    private final ChatGPTService chatGPTService;

    private final List<String> chatHistory = new ArrayList<>();

    private DialogMode mode = DialogMode.START;

    public TelegramBotContext(ChatGPTService chatGPTService) {
        this.chatGPTService = chatGPTService;
    }

    public ChatGPTService chatGPTService() {
        return chatGPTService;
    }

    public List<String> chatHistory() {
        return chatHistory;
    }

    public DialogMode getMode() {
        return mode;
    }

    public void setMode(DialogMode mode) {
        this.mode = mode;
    }
}
