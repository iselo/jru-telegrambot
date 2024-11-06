package com.javarush.telegram.profile;

import com.javarush.telegram.IUserInfoBuilder;

import java.util.Optional;

public abstract class AbstractQuestionHandler {

    private final IUserInfoBuilder userInfoBuilder;

    private final Optional<String> nextQuestion;

    public AbstractQuestionHandler(IUserInfoBuilder userInfoBuilder, Optional<String> nextQuestion) {
        this.userInfoBuilder = userInfoBuilder;
        this.nextQuestion = nextQuestion;
    }

    public IUserInfoBuilder userInfoBuilder() {
        return userInfoBuilder;
    }

    public Optional<String> apply(String messageText) {
        handle(messageText);
        return nextQuestion;
    }

    public abstract void handle(String messageText);
}
