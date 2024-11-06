package com.javarush.telegram;

import com.javarush.telegram.profile.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class TelegramBotContext {

    private final ChatGPTService chatGPTService;

    private final List<String> chatHistory = new ArrayList<>();

    private final List<AbstractQuestionHandler> questionHandlers = new ArrayList<>();

    private DialogMode mode = DialogMode.START;

    private final IUserInfoBuilder userInfoBuilder = UserInfo.newBuilder();

    public TelegramBotContext(ChatGPTService chatGPTService) {
        this.chatGPTService = chatGPTService;
        resetQuestions();
    }

    public void resetQuestions() {
        questionHandlers.clear();
        questionHandlers.addAll(
                List.of(
                        new ProfileNameQuestion(userInfoBuilder, Optional.of("Name")),
                        new ProfileSexQuestion(userInfoBuilder, Optional.of("Sex")),
                        new ProfileAgeQuestion(userInfoBuilder, Optional.of("Age")),
                        new ProfileLastQuestion2Question(userInfoBuilder, Optional.empty())
                )
        );
    }

    public ChatGPTService chatGPTService() {
        return chatGPTService;
    }

    public List<String> chatHistory() {
        return chatHistory;
    }

    public List<AbstractQuestionHandler> questionHandlers() {
        return questionHandlers;
    }

    public IUserInfoBuilder userInfoBuilder() {
        return userInfoBuilder;
    }

    public DialogMode getMode() {
        return mode;
    }

    public void setMode(DialogMode mode) {
        this.mode = mode;
    }
}
