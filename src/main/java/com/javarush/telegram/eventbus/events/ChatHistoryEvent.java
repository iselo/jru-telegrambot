package com.javarush.telegram.eventbus.events;

import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.eventbus.EventWithReturn;

import java.util.function.Consumer;

@Immutable
public final class ChatHistoryEvent extends EventWithReturn<String, String> {

    public ChatHistoryEvent(Consumer<String> consumer) {
        super(null, consumer);
    }
}
