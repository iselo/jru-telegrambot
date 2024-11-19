package com.javarush.telegram.eventbus.events;

import com.javarush.telegram.TelegramBotContext;
import com.javarush.telegram.responder.Responder;

public class DateCelebrityMessageEvent extends PayloadEvent {

    public DateCelebrityMessageEvent(Responder responder, TelegramBotContext context, String text) {
        super(responder, context, text);
    }
}
