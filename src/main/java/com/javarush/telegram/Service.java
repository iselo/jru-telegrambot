package com.javarush.telegram;

import com.google.common.eventbus.EventBus;

public enum Service {
    INSTANCE;

    private final EventBus eventBus = new EventBus();

    public EventBus eventBus() {
        return eventBus;
    }
}
