package com.javarush.telegram.eventbus.events;

import com.javarush.telegram.eventbus.Event;
import com.javarush.telegram.eventbus.Payload;
import com.javarush.telegram.responder.TextButtonsMessage;

public class TextButtonsMessageEvent extends Event<Payload<TextButtonsMessage>> {

    public TextButtonsMessageEvent(Payload<TextButtonsMessage> payload) {
        super(payload);
    }
}
