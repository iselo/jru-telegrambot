package com.javarush.telegram.eventbus.events;

import com.javarush.telegram.TelegramBotContext;
import com.javarush.telegram.responder.Responder;

import static com.google.common.base.Preconditions.checkNotNull;

public abstract class PayloadEvent extends Event {

    private final String payload;

    protected PayloadEvent(Responder responder, TelegramBotContext context, String payload) {
        super(responder, context);
        this.payload = checkNotNull(payload);
    }

    @Override
    public String toString() {
        return this.payload;
    }
}
