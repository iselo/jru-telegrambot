package com.javarush.telegram.eventbus.events;

import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.eventbus.Event;

@Immutable
public final class OpenerDialogEvent extends Event<String> {

    public OpenerDialogEvent() {
        super(null);
    }
}
