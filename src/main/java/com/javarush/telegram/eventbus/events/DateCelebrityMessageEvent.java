package com.javarush.telegram.eventbus.events;

import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.eventbus.Event;

@Immutable
public final class DateCelebrityMessageEvent extends Event<String> {

    public DateCelebrityMessageEvent(String payload) {
        super(payload);
    }
}
