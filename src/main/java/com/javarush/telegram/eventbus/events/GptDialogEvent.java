package com.javarush.telegram.eventbus.events;

import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.eventbus.Event;

@Immutable
public final class GptDialogEvent extends Event<String> {

    public GptDialogEvent() {
        super(null);
    }
}
