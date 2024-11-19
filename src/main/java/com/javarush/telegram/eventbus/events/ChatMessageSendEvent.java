package com.javarush.telegram.eventbus.events;

import com.javarush.telegram.TelegramBotContext;
import com.javarush.telegram.responder.Responder;

public class ChatMessageSendEvent extends PayloadEvent {

    public ChatMessageSendEvent(Responder responder, TelegramBotContext context, String text) {
        super(responder, context, text);
    }
}
