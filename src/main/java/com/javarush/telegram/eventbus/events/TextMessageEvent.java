package com.javarush.telegram.eventbus.events;

import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.eventbus.EventWithReturn;
import com.javarush.telegram.responder.TextMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.function.Consumer;

@Immutable
public final class TextMessageEvent extends EventWithReturn<TextMessage, Message> {

    public TextMessageEvent(TextMessage payload) {
        super(payload);
    }

    public TextMessageEvent(TextMessage payload, Consumer<Message> consumer) {
        super(payload, consumer);
    }
}
