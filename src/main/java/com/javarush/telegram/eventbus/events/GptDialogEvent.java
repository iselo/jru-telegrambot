package com.javarush.telegram.eventbus.events;

import com.javarush.telegram.TelegramBotContext;
import com.javarush.telegram.responder.Responder;

public class GptDialogEvent extends Event {

    public GptDialogEvent(Responder responder, TelegramBotContext context) {
        super(responder, context);
    }
}
