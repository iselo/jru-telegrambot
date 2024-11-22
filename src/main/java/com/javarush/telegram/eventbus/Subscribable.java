package com.javarush.telegram.eventbus;

/**
 * Adds functionality of registering and unregistering subscriber methods.
 */
public interface Subscribable extends Broadcastable {

    /**
     * Registers all subscriber methods on object to receive events.
     */
    default void register() {
        Bus.instance().register(this);
    }

    /**
     * Unregisters all subscriber methods on a registered object.
     */
    default void unregister() {
        Bus.instance().unregister(this);
    }
}
