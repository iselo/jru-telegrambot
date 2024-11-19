package com.javarush.telegram.eventbus.events;

import com.javarush.telegram.TelegramBotContext;
import com.javarush.telegram.responder.Responder;

public class DateDialogEvent extends Event {

    public DateDialogEvent(Responder responder, TelegramBotContext context) {
        super(responder, context);
    }
}
