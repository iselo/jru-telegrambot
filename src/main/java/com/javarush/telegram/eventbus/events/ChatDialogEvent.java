package com.javarush.telegram.eventbus.events;

import com.javarush.telegram.TelegramBotContext;
import com.javarush.telegram.responder.Responder;

public class ChatDialogEvent extends Event {

    public ChatDialogEvent(Responder responder, TelegramBotContext context) {
        super(responder, context);
    }
}
