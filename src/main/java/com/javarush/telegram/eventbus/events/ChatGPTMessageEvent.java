package com.javarush.telegram.eventbus.events;

import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.eventbus.EventWithReturn;

import java.util.function.Consumer;

@Immutable
public final class ChatGPTMessageEvent extends EventWithReturn<String, String> {
    public ChatGPTMessageEvent(String payload, Consumer<String> consumer) {
        super(payload, consumer);
    }
}
