package com.javarush.telegram.eventbus.events;

import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.eventbus.Event;

@Immutable
public final class DateCelebritySelectEvent extends Event<String> {

    public DateCelebritySelectEvent(String payload) {
        super(payload);
    }
}
