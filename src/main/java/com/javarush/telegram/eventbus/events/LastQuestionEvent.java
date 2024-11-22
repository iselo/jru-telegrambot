package com.javarush.telegram.eventbus.events;

import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.eventbus.Event;

@Immutable
public final class LastQuestionEvent extends Event<String> {

    public LastQuestionEvent(String payload) {
        super(payload);
    }
}
