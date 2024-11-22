package com.javarush.telegram.eventbus.events;

import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.eventbus.Event;

@Immutable
public final class ChatGPTPromptEvent extends Event<String> {

    public ChatGPTPromptEvent(String payload) {
        super(payload);
    }
}
