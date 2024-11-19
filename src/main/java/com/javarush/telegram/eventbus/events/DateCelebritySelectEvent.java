package com.javarush.telegram.eventbus.events;

import com.javarush.telegram.TelegramBotContext;
import com.javarush.telegram.responder.Responder;

public class DateCelebritySelectEvent extends PayloadEvent {

    public DateCelebritySelectEvent(Responder responder, TelegramBotContext context, String text) {
        super(responder, context, text);
    }
}
