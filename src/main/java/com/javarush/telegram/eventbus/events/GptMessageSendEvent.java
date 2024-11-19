package com.javarush.telegram.eventbus.events;

import com.javarush.telegram.TelegramBotContext;
import com.javarush.telegram.responder.Responder;

import static com.google.common.base.Preconditions.checkNotNull;

public class GptMessageSendEvent extends Event {

    private final String text;

    public GptMessageSendEvent(Responder responder,
                               TelegramBotContext context,
                               String text) {
        super(responder, context);
        this.text = checkNotNull(text);
    }

    public String text() {
        return text;
    }
}
