package com.javarush.telegram.eventbus.events;

import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.eventbus.Event;
import com.javarush.telegram.eventbus.Payload;
import com.javarush.telegram.responder.PhotoMessage;

@Immutable
public final class PhotoMessageEvent extends Event<Payload<PhotoMessage>> {

    public PhotoMessageEvent(Payload<PhotoMessage> payload) {
        super(payload);
    }
}
