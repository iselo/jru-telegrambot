package com.javarush.telegram.eventbus.events;

import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.TelegramBotContext;
import com.javarush.telegram.responder.Responder;

@Immutable
public final class ProfileDialogEvent extends Event {

    public ProfileDialogEvent(Responder responder, TelegramBotContext context) {
        super(responder, context);
    }
}
