package com.javarush.telegram.eventbus.events;

import com.javarush.telegram.TelegramBotContext;
import com.javarush.telegram.responder.Responder;

import static com.google.common.base.Preconditions.checkNotNull;

public abstract class PayloadEvent extends Event {

    private final String text;

    protected PayloadEvent(Responder responder, TelegramBotContext context, String text) {
        super(responder, context);
        this.text = checkNotNull(text);
    }

    @Override
    public String toString() {
        return this.text;
    }
}
