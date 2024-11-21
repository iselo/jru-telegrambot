package com.javarush.telegram.eventbus.events;

import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.eventbus.Event;
import com.javarush.telegram.eventbus.Payload;
import com.javarush.telegram.responder.TextButtonsMessage;

@Immutable
public final class TextButtonsMessageEvent extends Event<Payload<TextButtonsMessage>> {

    public TextButtonsMessageEvent(Payload<TextButtonsMessage> payload) {
        super(payload);
    }
}
