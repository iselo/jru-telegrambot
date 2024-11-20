package com.javarush.telegram.eventbus.events;

import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.eventbus.Event;
import com.javarush.telegram.eventbus.Payload;
import com.javarush.telegram.responder.UpdatedTextMessage;

@Immutable
public final class UpdatedTextMessageEvent extends Event<Payload<UpdatedTextMessage>> {

    public UpdatedTextMessageEvent(Payload<UpdatedTextMessage> payload) {
        super(payload);
    }
}
