package com.javarush.telegram.eventbus.events;

import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.TelegramBotContext;
import com.javarush.telegram.responder.Responder;

import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;

@Immutable
public final class ProfileQuestionEvent extends Event {

    private final Optional<String> previousAnswer;

    public ProfileQuestionEvent(Responder responder,
                                TelegramBotContext context,
                                Optional<String> previousAnswer) {
        super(responder, context);
        this.previousAnswer = checkNotNull(previousAnswer);
    }

    public Optional<String> previousAnswer() {
        return previousAnswer;
    }
}
