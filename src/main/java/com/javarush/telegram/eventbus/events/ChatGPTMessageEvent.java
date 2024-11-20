package com.javarush.telegram.eventbus.events;

import com.javarush.telegram.eventbus.Event;
import com.javarush.telegram.eventbus.Payload;

import java.util.function.Consumer;

import static com.google.common.base.Preconditions.checkNotNull;

public class ChatGPTMessageEvent extends Event<Payload<String>> {

    @SuppressWarnings("Immutable")
    private final Consumer<String> consumer;

    public ChatGPTMessageEvent(Payload<String> payload) {
        super(payload);
        this.consumer = null;
    }

    public ChatGPTMessageEvent(Payload<String> payload, Consumer<String> consumer) {
        super(payload);
        this.consumer = checkNotNull(consumer);
    }

    public Consumer<String> consumer() {
        return consumer;
    }
}
