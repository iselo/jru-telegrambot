package com.javarush.telegram;

import com.google.common.eventbus.EventBus;

/**
 * This singleton provides EventBus service.
 */
public enum Service {
    INSTANCE;

    @SuppressWarnings("Immutable")
    private final EventBus eventBus = new EventBus();

    /**
     * Returns ${code EventBus} instance.
     */
    public EventBus eventBus() {
        return eventBus;
    }
}
