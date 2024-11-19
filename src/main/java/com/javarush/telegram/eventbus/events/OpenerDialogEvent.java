package com.javarush.telegram.eventbus.events;

import com.javarush.telegram.TelegramBotContext;
import com.javarush.telegram.responder.Responder;

public class OpenerDialogEvent extends Event {

    public OpenerDialogEvent(Responder responder, TelegramBotContext context) {
        super(responder, context);
    }
}
