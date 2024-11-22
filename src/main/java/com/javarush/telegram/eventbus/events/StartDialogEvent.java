package com.javarush.telegram.eventbus.events;

import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.eventbus.Event;

@Immutable
public final class StartDialogEvent extends Event<String> {

    public StartDialogEvent() {
        super(null);
    }
}
