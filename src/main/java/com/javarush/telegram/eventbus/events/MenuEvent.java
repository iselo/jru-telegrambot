package com.javarush.telegram.eventbus.events;

import com.javarush.telegram.eventbus.Event;
import com.javarush.telegram.eventbus.Payload;
import com.javarush.telegram.responder.Menu;

public class MenuEvent extends Event<Payload<Menu>> {

    public MenuEvent(Payload<Menu> payload) {
        super(payload);
    }
}
