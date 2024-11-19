package com.javarush.telegram.eventbus.events;

import com.javarush.telegram.TelegramBotContext;
import com.javarush.telegram.responder.Responder;

public class MenuInitializationEvent extends Event {

    public MenuInitializationEvent(Responder responder, TelegramBotContext context) {
        super(responder, context);
    }
}
