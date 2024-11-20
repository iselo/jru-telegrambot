package com.javarush.telegram.eventbus.events;

import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.eventbus.Event;
import com.javarush.telegram.eventbus.Payload;

import java.util.Optional;

@Immutable
public final class OpenerQuestionEvent extends Event<Payload<Optional<String>>> {

    public OpenerQuestionEvent(Payload<Optional<String>> payload) {
        super(payload);
    }
}
