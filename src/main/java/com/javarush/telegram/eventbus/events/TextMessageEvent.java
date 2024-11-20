package com.javarush.telegram.eventbus.events;

import com.javarush.telegram.eventbus.Event;
import com.javarush.telegram.eventbus.Payload;
import com.javarush.telegram.responder.TextMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.function.Consumer;

import static com.google.common.base.Preconditions.checkNotNull;

public class TextMessageEvent extends Event<Payload<TextMessage>> {

    @SuppressWarnings("Immutable")
    private final Consumer<Message> consumer;

    public TextMessageEvent(Payload<TextMessage> payload) {
        super(payload);
        this.consumer = null;
    }

    public TextMessageEvent(Payload<TextMessage> payload, Consumer<Message> consumer) {
        super(payload);
        this.consumer = checkNotNull(consumer);
    }

    public Consumer<Message> consumer() {
        return consumer;
    }
}
