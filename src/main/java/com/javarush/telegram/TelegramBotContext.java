package com.javarush.telegram;

import com.javarush.telegram.survey.UserInfoSurvey;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.javarush.telegram.DialogMode.START;

public final class TelegramBotContext implements BotReadOnlyContext {

    private final ChatGPTService chatGPTService;
    private final List<String> chatHistory = new ArrayList<>();
    private final UserInfoSurvey survey = new UserInfoSurvey();

    private DialogMode mode = START;

    public TelegramBotContext(ChatGPTService chatGPTService) {
        this.chatGPTService = checkNotNull(chatGPTService);
    }

    /**
     * Returns ChatGPT service.
     */
    public ChatGPTService chatGPTService() {
        return chatGPTService;
    }

    /**
     * Returns chat history.
     */
    public List<String> chatHistory() {
        return chatHistory;
    }

    /**
     * @inheritDoc
     */
    public UserInfoSurvey survey() {
        return survey;
    }

    /**
     * @inheritDoc
     */
    public DialogMode getMode() {
        return mode;
    }

    /**
     * Sets current Telegram bot dialog mode.
     */
    public void setMode(DialogMode mode) {
        checkNotNull(mode);
        this.mode = mode;
    }
}
