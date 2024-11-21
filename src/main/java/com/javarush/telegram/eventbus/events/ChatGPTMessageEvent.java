package com.javarush.telegram.eventbus.events;

import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.eventbus.EventWithConsumer;
import com.javarush.telegram.eventbus.Payload;

import java.util.function.Consumer;

@Immutable
public final class ChatGPTMessageEvent extends EventWithConsumer<Payload<String>, String> {
    public ChatGPTMessageEvent(Payload<String> payload, Consumer<String> consumer) {
        super(payload, consumer);
    }
}
