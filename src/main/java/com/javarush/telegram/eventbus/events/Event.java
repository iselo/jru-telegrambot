package com.javarush.telegram.eventbus.events;

import com.javarush.telegram.TelegramBotContext;
import com.javarush.telegram.responder.Responder;

import static com.google.common.base.Preconditions.checkNotNull;

public abstract class Event {

    private final Responder responder;
    private final TelegramBotContext context;

    public Event(Responder responder, TelegramBotContext context) {
        this.responder = checkNotNull(responder);
        this.context = checkNotNull(context);
    }

    public Responder responder() {
        return responder;
    }

    public TelegramBotContext context() {
        return context;
    }
}
