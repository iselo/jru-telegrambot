package com.javarush.telegram.eventbus.events;

import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.eventbus.Event;

@Immutable
public final class GptMessageSendEvent extends Event<String> {

    public GptMessageSendEvent(String payload) {
        super(payload);
    }
}
