package com.javarush.telegram.eventbus.events;

import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.eventbus.Event;

@Immutable
public final class ChatMessageSendEvent extends Event<String> {

    public ChatMessageSendEvent(String payload) {
        super(payload);
    }
}
