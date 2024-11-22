package com.javarush.telegram.eventbus.events;

import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.eventbus.Event;

import java.util.Optional;

@Immutable
public final class ProfileQuestionEvent extends Event<Optional<String>> {

    public ProfileQuestionEvent(Optional<String> payload) {
        super(payload);
    }
}
