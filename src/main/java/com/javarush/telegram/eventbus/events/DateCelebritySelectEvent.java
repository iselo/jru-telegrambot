package com.javarush.telegram.eventbus.events;

import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.TelegramBotContext;
import com.javarush.telegram.responder.Responder;

@Immutable
public final class DateCelebritySelectEvent extends PayloadEvent {

    public DateCelebritySelectEvent(Responder responder, TelegramBotContext context, String text) {
        super(responder, context, text);
    }
}
