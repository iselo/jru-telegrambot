package com.javarush.telegram.questions;

import com.javarush.telegram.IUserInfoBuilder;

import javax.annotation.concurrent.Immutable;
import java.util.Optional;

@Immutable
public final class NameQuestion extends AbstractQuestionHandler {

    public NameQuestion(IUserInfoBuilder userInfoBuilder, Optional<String> nextQuestion) {
        super(userInfoBuilder, nextQuestion);
    }

    @Override
    protected void handle(String messageText) {
        // Intentionally empty.
    }
}
