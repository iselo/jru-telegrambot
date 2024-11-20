package com.javarush.telegram.eventbus.events;

import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.eventbus.Event;
import com.javarush.telegram.eventbus.Payload;

@Immutable
public final class GptMessageSendEvent extends Event<Payload<String>> {

    public GptMessageSendEvent(Payload<String> payload) {
        super(payload);
    }
}
