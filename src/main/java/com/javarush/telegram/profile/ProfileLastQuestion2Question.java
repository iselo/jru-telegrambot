package com.javarush.telegram.profile;

import com.javarush.telegram.IUserInfoBuilder;

import javax.annotation.concurrent.Immutable;
import java.util.Optional;

@Immutable
public final class ProfileLastQuestion2Question extends AbstractQuestionHandler {

    public ProfileLastQuestion2Question(IUserInfoBuilder userInfoBuilder, Optional<String> nextQuestion) {
        super(userInfoBuilder, nextQuestion);
    }

    @Override
    protected void handle(String messageText) {
        this.userInfoBuilder().setAge(messageText);
    }
}
