package com.javarush.telegram.eventbus.events;

import com.google.errorprone.annotations.Immutable;
import com.javarush.telegram.eventbus.Event;
import com.javarush.telegram.responder.Menu;

@Immutable
public final class MenuEvent extends Event<Menu> {

    public MenuEvent(Menu payload) {
        super(payload);
    }
}
