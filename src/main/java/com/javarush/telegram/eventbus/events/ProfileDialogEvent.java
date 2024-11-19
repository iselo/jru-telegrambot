package com.javarush.telegram.eventbus.events;

import com.javarush.telegram.TelegramBotContext;
import com.javarush.telegram.responder.Responder;

public class ProfileDialogEvent extends Event {

    public ProfileDialogEvent(Responder responder, TelegramBotContext context) {
        super(responder, context);
    }
}
