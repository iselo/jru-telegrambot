package com.javarush.telegram.profile;

import com.javarush.telegram.IUserInfoBuilder;

import javax.annotation.concurrent.Immutable;
import java.util.Optional;

@Immutable
public final class ProfileNameQuestion extends AbstractQuestionHandler {

    public ProfileNameQuestion(IUserInfoBuilder userInfoBuilder, Optional<String> nextQuestion) {
        super(userInfoBuilder, nextQuestion);
    }

    @Override
    public void handle(String messageText) {
        // Intentionally empty.
    }
}
