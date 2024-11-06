package com.javarush.telegram.profile;

import com.javarush.telegram.IUserInfoBuilder;

import javax.annotation.concurrent.Immutable;
import java.util.Optional;

@Immutable
public final class ProfileSexQuestion extends AbstractQuestionHandler {

    public ProfileSexQuestion(IUserInfoBuilder userInfoBuilder, Optional<String> nextQuestion) {
        super(userInfoBuilder, nextQuestion);
    }

    @Override
    protected void handle(String messageText) {
        this.userInfoBuilder().setName(messageText);
    }
}
