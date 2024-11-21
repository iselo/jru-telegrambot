package com.javarush.telegram.eventbus.events;

import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.eventbus.EventWithConsumer;
import com.javarush.telegram.eventbus.Payload;

import java.util.function.Consumer;

@Immutable
public final class ChatHistoryEvent extends EventWithConsumer<Payload<String>, String> {

    public ChatHistoryEvent(Payload<String> payload, Consumer<String> consumer) {
        super(payload, consumer);
    }
}
