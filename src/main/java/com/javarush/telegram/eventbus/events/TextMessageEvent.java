package com.javarush.telegram.eventbus.events;

import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.eventbus.EventWithConsumer;
import com.javarush.telegram.eventbus.Payload;
import com.javarush.telegram.responder.TextMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.function.Consumer;

@Immutable
public final class TextMessageEvent extends EventWithConsumer<Payload<TextMessage>, Message> {

    public TextMessageEvent(Payload<TextMessage> payload) {
        super(payload);
    }

    public TextMessageEvent(Payload<TextMessage> payload, Consumer<Message> consumer) {
        super(payload, consumer);
    }
}
