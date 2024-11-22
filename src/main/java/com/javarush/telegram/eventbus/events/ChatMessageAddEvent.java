package com.javarush.telegram.eventbus.events;

import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.eventbus.Event;

@Immutable
public final class ChatMessageAddEvent extends Event<String> {

    public ChatMessageAddEvent(String payload) {
        super(payload);
    }
}
