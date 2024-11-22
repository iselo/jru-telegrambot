package com.javarush.telegram.eventbus.events;

import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.eventbus.Event;

@Immutable
public final class AskQuestionEvent extends Event<String> {

    public AskQuestionEvent(String payload) {
        super(payload);
    }
}
