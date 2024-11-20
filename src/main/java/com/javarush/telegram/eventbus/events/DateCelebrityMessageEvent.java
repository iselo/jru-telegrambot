package com.javarush.telegram.eventbus.events;

import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.TelegramBotContext;
import com.javarush.telegram.responder.Responder;

@Immutable
public final class DateCelebrityMessageEvent extends PayloadEvent {

    public DateCelebrityMessageEvent(Responder responder, TelegramBotContext context, String payload) {
        super(responder, context, payload);
    }
}
