package com.javarush.telegram.eventbus.events;

import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.eventbus.Event;
import com.javarush.telegram.responder.TextButtonsMessage;

@Immutable
public final class TextButtonsMessageEvent extends Event<TextButtonsMessage> {

    public TextButtonsMessageEvent(TextButtonsMessage payload) {
        super(payload);
    }
}
